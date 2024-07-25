package org.example.aston_trainee_hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.ExceptionMessageHelper;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.repository.LocalityRepository;
import org.example.aston_trainee_hw3.service.LocalityService;
import org.example.aston_trainee_hw3.weather_api.WeatherApi;
import org.example.aston_trainee_hw3.weather_api.exception.WeatherApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.example.aston_trainee_hw3.mapper.LocalityMapper.INSTANCE;


/**
 * Implementation of {@link LocalityService} interface.
 * Provides CRUD operations and additional methods to manage localities.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository repository;
    private final WeatherApi weatherApi;

    @Override
    public LocalityDto findById(Long id) {
        validateId(id);
        return INSTANCE.toDto(repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.entityNotFoundMsg(id))));
    }

    @Override
    public List<LocalityDto> findAll()  {
        return INSTANCE.toDtoList(repository.findAll());
    }

    @Override
    public LocalityDto findByIdWithRec(Long id) {
        validateId(id);

        LocalityEntity locality = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.entityNotFoundMsg(id)));

        return getDtoWithRecFromEntity(locality);
    }

    @Override
    public List<LocalityDto> findAllWithRec() {
        List<LocalityEntity> entities = repository.findAll();

        return entities.stream().map(this::getDtoWithRecFromEntity).toList();
    }

    @Transactional
    @Override
    public LocalityDto save(LocalityDto localityDto) {
        validateEntity(localityDto, true);
        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(localityDto)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        validateId(id);
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public LocalityDto update(LocalityDto localityDto, Long id) {
        validateId(localityDto.getId());
        validateId(id);
        validateMatchingIds(localityDto.getId(), id);

        validateEntity(localityDto, false);

        LocalityEntity entity = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessageHelper.entityNotFoundMsg(id)));

        entity.setPopulation(localityDto.getPopulation());
        entity.setHasMetro(localityDto.getHasMetro());


        return INSTANCE.toDto(repository.save(INSTANCE.toEntity(localityDto)));
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
     * @param localityDto the entity to validate
     * @param isNew if the entity is new to validate
     * @throws InvalidEntityException if the entity is not valid.
     */
    private void validateEntity(LocalityDto localityDto, Boolean isNew) {
        StringBuilder sb = new StringBuilder();

        if (Boolean.TRUE.equals(isNew && localityDto.getName() == null) || localityDto.getName().isEmpty()) {
            sb.append("Entity name can't be null or empty. ");
        }
        if (localityDto.getPopulation() == null || localityDto.getPopulation() <= 0) {
            sb.append("Population can't be null or less than 0. ");
        }
        if (localityDto.getHasMetro() == null) {
            sb.append("Metro can't be null or empty.");
        }

        if (!sb.isEmpty()) {
            throw new InvalidEntityException(sb.toString());
        }
    }


    private LocalityDto getDtoWithRecFromEntity(LocalityEntity locality) {
        String rec;
        LocalityDto localityDto = INSTANCE.toDto(locality);

        try {
            rec = weatherApi.getWeatherConditionByLocalityName(locality.getName());
        }
        catch (WeatherApiException e) {
            rec = e.getMessage();
        }
        catch (RestClientException e) {
            rec = "По техническим причинам, в данный момент невозможно загрузить данные о погодных условиях.";
        }

        localityDto.setRecommendation(rec);

        return localityDto;
    }
}
