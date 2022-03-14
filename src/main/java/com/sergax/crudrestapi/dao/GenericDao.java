package com.sergax.crudrestapi.dao;

import java.util.List;

public interface GenericDao<T, Id> {

    T getById(Long id);

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(Long id);
}
