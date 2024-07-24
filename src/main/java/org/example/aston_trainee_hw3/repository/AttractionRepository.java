package org.example.aston_trainee_hw3.repository;

import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link AttractionEntity} entities.
 * <p>
 * This interface provides methods for performing CRUD operations
 * and custom queries on the {@link AttractionEntity} entity.
 **/
@Repository
public interface AttractionRepository extends JpaRepository<AttractionEntity, Long>, JpaSpecificationExecutor<AttractionEntity> {

    List<AttractionEntity> findAllByLocalityIdOrderByName(Long localityId);

}
