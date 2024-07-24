package org.example.aston_trainee_hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.ExceptionMessageHelper;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.repository.LocalityRepository;
import org.example.aston_trainee_hw3.service.LocalityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.aston_trainee_hw3.mapper.LocalityMapper.INSTANCE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository repository;

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

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidEntityException("Entity Id can't be null or less than 0");
        }
    }

    private void validateMatchingIds(Long entityId, Long urlId) {
        if (!entityId.equals(urlId)) {
            throw new InvalidEntityException("Entity Id and path Id must be equals");
        }
    }

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


}
