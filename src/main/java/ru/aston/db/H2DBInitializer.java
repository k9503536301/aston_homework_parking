package ru.aston.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.aston.model.ParkingSpot;
import ru.aston.model.Ticket;
import ru.aston.model.User;
import ru.aston.model.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Statement;

public class H2DBInitializer implements DBInitializer {

    private final SessionFactory sessionFactory;

    public H2DBInitializer() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(ParkingSpot.class);
            configuration.addAnnotatedClass(Ticket.class);
            configuration.addAnnotatedClass(Vehicle.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex.getMessage() + ex.getStackTrace());
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void initialize() {
        try (Session session = sessionFactory.openSession()) {
            String filePath = Paths.get("src", "main", "resources", "init_db.sql").toString();
            File sqlFile = new File(filePath);

            try (InputStream inputStream = new FileInputStream(sqlFile)) {
                String sql = new String(inputStream.readAllBytes());

                session.doWork(connection -> {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(sql);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при чтении SQL-скрипта", e);
            }
        }

    }
}