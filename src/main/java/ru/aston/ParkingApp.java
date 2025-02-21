package ru.aston;
import org.hibernate.SessionFactory;
import ru.aston.dao.*;

import ru.aston.db.DBInitializer;

import org.h2.tools.Server;
import ru.aston.db.H2DBInitializer;
import ru.aston.util.ParkingMenuUtil;

import java.sql.SQLException;

public class ParkingApp {

    public static void main (String[] args) throws SQLException {
        DBInitializer initializer = new H2DBInitializer();
        initializer.initialize();
        SessionFactory sessionFactory = initializer.getSessionFactory();

        Server h2WebServer = org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
        h2WebServer.start();

        while(true){
            ParkingMenuUtil.printMainMenu();
            int k = ParkingMenuUtil.readChoice();

            switch (k){
                case 1:{
                    UserDao userDao = new UserDao(sessionFactory);
                    ParkingMenuUtil.displaySubMenu(userDao);
                    break;
                }

                case 2:{
                    TicketDao ticketDao = new TicketDao(sessionFactory);
                    ParkingMenuUtil.displaySubMenu(ticketDao);
                    break;
                }

                case 3:{
                    ParkingSpotDao parkingSpotDao =  new ParkingSpotDao(sessionFactory);
                    ParkingMenuUtil.displaySubMenu(parkingSpotDao);
                    break;
                }

                case 4:{
                    VehicleDao vehicleDao = new VehicleDao(sessionFactory);
                    ParkingMenuUtil.displaySubMenu(vehicleDao);
                    break;
                }

                case 5: {
                    h2WebServer.stop();
                    return;
                }
                default:{System.out.println("Wrong key");}
            }
        }
    }
}
