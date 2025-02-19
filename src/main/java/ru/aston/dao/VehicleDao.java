package ru.aston.dao;

import org.hibernate.SessionFactory;
import ru.aston.model.Vehicle;

public class VehicleDao extends AbstractDao<Vehicle> {
    public VehicleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Vehicle> getEntityClass() {
        return Vehicle.class;
    }
}
