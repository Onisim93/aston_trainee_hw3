package org.example.aston_trainee_hw3.repository;

import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link LocalityEntity} entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and custom queries on the {@link LocalityEntity} entity.
 **/
@Repository
public interface LocalityRepository extends JpaRepository<LocalityEntity, Long> {

}
