package com.daddarioc.petclinic.services;

import java.util.Set;

/**
 * Common CRUD interface to be implemented by the use case specific classes
 * @param <T>
 * @param <Id>
 */
public interface CrudService<T, ID> {

    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
