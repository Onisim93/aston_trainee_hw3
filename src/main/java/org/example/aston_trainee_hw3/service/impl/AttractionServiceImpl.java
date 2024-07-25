package org.example.aston_trainee_hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.ExceptionMessageHelper;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.repository.AttractionRepository;
import org.example.aston_trainee_hw3.service.AttractionService;
import org.example.aston_trainee_hw3.service.specification.AttractionSpecification;
import org.example.aston_trainee_hw3.utils.SortUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.aston_trainee_hw3.mapper.AttractionMapper.INSTANCE;

/**
 * Implementation of {@link AttractionService} interface.
 * Provides CRUD operations and additional methods to manage attractions.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository repository;

    @Override
    public AttractionDto findById(Long id) {
        validateId(id);
        return INSTANCE.toDto(repository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        ExceptionMessageHelper.entityNotFoundMsg(id))));
    }

    @Override
    public List<AttractionDto> findAll() {
        return INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public List<AttractionDto> findByCriteria(String sortBy, String type, String localityName) {
        Specification<AttractionEntity> specs = AttractionSpecification.hasType(type);
        specs = specs.and(AttractionSpecification.hasLocalityName(localityName));

        Sort sort = SortUtils.getSort(sortBy);
        return INSTANCE.toDtoList(repository.findAll(specs, sort));
    }

    @Override
    public List<AttractionDto> findByLocalityId(Long localityId) {
        validateId(localityId);
        return INSTANCE.toDtoList(repository.findAllByLocalityIdOrderByName(localityId));
    }

    @Transactional
    @Override
    public AttractionDto save(AttractionDto attractionDto) {
        validateEntity(attractionDto);
        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(attractionDto)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        validateId(id);
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public AttractionDto update(AttractionDto attractionDto, Long id) {
        validateId(attractionDto.getId());
        validateId(id);
        validateMatchingIds(attractionDto.getId(), id);

        AttractionEntity attractionEntity = repository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                ExceptionMessageHelper.entityNotFoundMsg(id)));

        if (attractionDto.getDescription() != null && !attractionDto.getDescription().isBlank()) {
            attractionEntity.setDescription(attractionDto.getDescription());
        }
        else {
            throw new InvalidEntityException("Description can't be null or empty.");
        }

        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(attractionDto)));
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
     * @param attractionDto the entity to validate
     * @throws InvalidEntityException if the entity is not valid.
     */
    private void validateEntity(AttractionDto attractionDto) {
        StringBuilder sb = new StringBuilder();

        if (attractionDto.getName() == null || attractionDto.getName().isEmpty()) {
            sb.append("Name can't be null or empty. ");
        }
        if (attractionDto.getDescription() == null || attractionDto.getDescription().isEmpty()) {
            sb.append("Description can't be null or empty. ");
        }
        if (attractionDto.getCreationDate() == null) {
            sb.append("Creation date can't be null. ");
        }

        if (attractionDto.getLocalityId() == null || attractionDto.getLocalityId() <= 0) {
            sb.append("Locality id can't be null or less than 0. ");
        }

        if (attractionDto.getServiceIds() == null || attractionDto.getServiceIds().isEmpty()) {
            sb.append("ServiceIds must have at least one serviceId.");
        }

        if (!sb.isEmpty()) {
            throw new InvalidEntityException(sb.toString());
        }
    }

}
