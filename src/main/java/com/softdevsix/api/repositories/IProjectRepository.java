package com.softdevsix.api.repositories;

import java.util.UUID;

public interface IProjectRepository<T> {
    void save(T proyect);

    T findById(UUID proyectId);

    void delete(UUID proyectId);
}
