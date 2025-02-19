package ru.aston.dao;

import org.hibernate.SessionFactory;
import ru.aston.model.Ticket;

public class TicketDao extends AbstractDao<Ticket> {
    public TicketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}