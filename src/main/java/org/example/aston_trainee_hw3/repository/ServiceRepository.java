package org.example.aston_trainee_hw3.repository;

import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link ServiceEntity} entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and custom queries on the {@link ServiceEntity} entity.
 **/
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

}
