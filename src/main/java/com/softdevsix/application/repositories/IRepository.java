package com.softdevsix.application.repositories;

import java.util.UUID;

public interface IRepository<T> {
    boolean add(T entity);

    T getById(UUID id);

    T update(UUID id, T entity);

    boolean delete(UUID id);
}
