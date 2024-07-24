package org.example.aston_trainee_hw3.service;

import java.util.List;

/**
 * Base interface for defining common operations on entities.
 *
 * @param <T> the type of entity managed by this service
 * @param <K> the type of the entity's identifier (primary key)
 */
public interface BaseService<T, K> {

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity to find
     * @return the found entity
     *
     * @throws jakarta.persistence.EntityNotFoundException if an entity not found
     */
    T findById(K id);

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities or an empty list
     */
    List<T> findAll();

    /**
     * Saves an entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @throws jakarta.persistence.EntityNotFoundException if an entity not found
     */
    void deleteById(K id);

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @param id     an id of the entity
     * @return the updated entity
     *
     * @throws jakarta.persistence.EntityNotFoundException if an entity not found
     */
    T update(T entity, K id);
}
