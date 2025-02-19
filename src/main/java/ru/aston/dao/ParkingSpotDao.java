package ru.aston.dao;

import org.hibernate.SessionFactory;
import ru.aston.model.ParkingSpot;

public class ParkingSpotDao extends AbstractDao<ParkingSpot> {

    public ParkingSpotDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<ParkingSpot> getEntityClass() {
        return ParkingSpot.class;
    }
}
