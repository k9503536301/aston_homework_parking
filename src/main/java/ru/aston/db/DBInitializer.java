package ru.aston.db;

import org.hibernate.SessionFactory;

public interface DBInitializer {

    void initialize();
    SessionFactory getSessionFactory();
}
