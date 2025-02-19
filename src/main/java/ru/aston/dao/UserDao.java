package ru.aston.dao;

import org.hibernate.SessionFactory;
import ru.aston.model.User;

public class UserDao extends AbstractDao<User> {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
