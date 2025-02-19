package ru.aston.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findById(int id);
    void deleteById(int id);
}

