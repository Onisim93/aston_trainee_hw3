package org.example.aston_trainee_hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.ExceptionMessageHelper;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.repository.ServiceRepository;
import org.example.aston_trainee_hw3.service.ServiceEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.aston_trainee_hw3.mapper.ServiceMapper.INSTANCE;


/**
 * Implementation of {@link ServiceEntityService} interface.
 * Provides CRUD operations and additional methods to manage services.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceEntityServiceImpl implements ServiceEntityService {
    
    private final ServiceRepository repository;

    @Override
    public ServiceDto findById(Long id) {
        validateId(id);
        return INSTANCE.toDto(repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.entityNotFoundMsg(id))));
    }

    @Override
    public List<ServiceDto> findAll() {
        return INSTANCE.toDtoList(repository.findAll());
    }

    @Transactional
    @Override
    public ServiceDto save(ServiceDto serviceDto) {
        validateEntity(serviceDto);
        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(serviceDto)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        validateId(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public ServiceDto update(ServiceDto serviceDto, Long id) {
        validateId(serviceDto.getId());
        validateId(id);
        validateMatchingIds(serviceDto.getId(), id);
        validateEntity(serviceDto);

        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(serviceDto)));
    }

    /**
     * Validates if the provided ID is valid (i.e., not null and greater than zero).
     *
     * @param id the ID to validate
     * @throws InvalidEntityException if the ID is null or less than or equal to zero
     */
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidEntityException("Entity Id can't be null or less than 0");
        }
    }

    /**
     * Validates if the provided entity ID matches the URL ID.
     *
     * @param entityId the ID of the entity to validate
     * @param urlId ID from the URL to compare against
     * @throws InvalidEntityException if entityId is not equal to urlId
     */
    private void validateMatchingIds(Long entityId, Long urlId) {
        if (!entityId.equals(urlId)) {
            throw new InvalidEntityException("Entity Id and path Id must be equals");
        }
    }

    /**
     * Validates if the provided entity is valid.
     *
     * @param serviceDto the entity to validate
     * @throws InvalidEntityException if the entity is not valid.
     */
    private void validateEntity(ServiceDto serviceDto) {
        StringBuilder sb = new StringBuilder();

        if (serviceDto.getName() == null || serviceDto.getName().isEmpty()) {
            sb.append("Service name can't be null or empty. ");
        }
        if (serviceDto.getDescription() == null || serviceDto.getDescription().isEmpty()) {
            sb.append("Service description can't be null or empty.");
        }

        if (!sb.isEmpty()) {
            throw new InvalidEntityException(sb.toString());
        }
    }
}
