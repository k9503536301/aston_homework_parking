package ru.aston.dao;

import java.util.List;
import java.util.Optional;

public interface SimpleDao<T> {
    void save(T record);
    List<T> findAll();
    Optional<T> findById(int id);
    void deleteById(int id);
}

