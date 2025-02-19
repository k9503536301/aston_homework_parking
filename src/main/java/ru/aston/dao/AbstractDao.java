package ru.aston.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements BaseDao<T> {

    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session openSession() {
        return sessionFactory.openSession();
    }

    protected void close(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void save(T entity) {
        Session session = openSession();
        try {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            close(session);
        }
    }

    @Override
    public Optional<T> findById(int id) {
        Session session = openSession();

        try {
            T result = session.find(getEntityClass(), id);
            return Optional.ofNullable(result);
        } finally {
            close(session);
        }
    }

    @Override
    public List<T> findAll() {
        Session session = openSession();

        try {
            return session.createQuery("from " + getEntityClass().getSimpleName(), getEntityClass()).list();
        } finally {
            close(session);
        }
    }

    @Override
    public void deleteById(int id) {
        Session session = openSession();

        try {
            session.beginTransaction();
            Optional<T> entity = this.findById(id);
            session.delete(entity.get());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            close(session);
        }
    }

    protected abstract Class<T> getEntityClass();
}
