package org.example.aston_trainee_hw3.service;

import org.example.aston_trainee_hw3.dto.LocalityDto;

import java.util.List;

/**
 * Service interface for managing localities.
 * Extends the {@link BaseService} interface for CRUD operations.
 */
public interface LocalityService extends BaseService<LocalityDto, Long> {

    LocalityDto findByIdWithRec(Long id);
    List<LocalityDto> findAllWithRec();


}
