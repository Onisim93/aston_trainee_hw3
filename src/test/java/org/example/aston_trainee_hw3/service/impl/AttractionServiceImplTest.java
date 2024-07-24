package org.example.aston_trainee_hw3.service.impl;

import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.MockTestEntityData;
import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.example.aston_trainee_hw3.repository.AttractionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttractionServiceImplTest {

    @Mock
    private AttractionRepository repository;

    @InjectMocks
    private AttractionServiceImpl service;

    @Test
    void findById() {
        AttractionDto expected = MockTestDtoData.firstAttraction;
        AttractionEntity model = MockTestEntityData.firstAttraction;
        when(repository.findById(expected.getId())).thenReturn(Optional.of(model));
        AttractionDto actual = service.findById(expected.getId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void findByNotExistsId() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(150l));
    }

    @Test
    void findByNotValidId() {
        Assertions.assertThrows(InvalidEntityException.class, () -> service.findById(-1l));
    }

    @Test
    void findAll() {
        List<AttractionEntity> models = MockTestEntityData.allAttractions;
        List<AttractionDto> expected = MockTestDtoData.allAttractions;
        when(repository.findAll()).thenReturn(models);
        List<AttractionDto> actual = service.findAll();

        assertNotNull(actual);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void findByCriteria() {
        List<AttractionEntity> models = MockTestEntityData.museumAttractionsForSamara;
        List<AttractionDto> expected = MockTestDtoData.museumAttractionsForSamara;
        String type = "MUSEUM";
        String sortBy = "name::desc";
        String locality = "самара";

        when(repository.findAll(any(Specification.class), any(Sort.class))).thenReturn(models);

        List<AttractionDto> actual = service.findByCriteria(sortBy, type, locality);

        assertNotNull(actual);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

    }

    @Test
    void findByLocalityId() {
        List<AttractionDto> expected = MockTestDtoData.samaraAttractions;
        List<AttractionEntity> models = MockTestEntityData.samaraAttractions;
        Long samaraId = MockTestDtoData.firstLocality.getId();

        when(repository.findAllByLocalityIdOrderByName(samaraId)).thenReturn(models);

        List<AttractionDto> actual = service.findByLocalityId(samaraId);

        assertNotNull(actual);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void save() {
        AttractionEntity model = MockTestEntityData.newAttraction;
        AttractionEntity modelCreated = MockTestEntityData.newAttractionCreated;

        AttractionDto newAttractionDto = MockTestDtoData.newAttraction;
        newAttractionDto.setServiceIds(modelCreated.getServiceEntities().stream().map(ServiceEntity::getId).toList());
        newAttractionDto.setLocalityId(model.getLocality().getId());

        AttractionDto expected = MockTestDtoData.newAttractionCreated;

        when(repository.save(model)).thenReturn(modelCreated);
        AttractionDto actual = service.save(newAttractionDto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        AttractionDto dto = MockTestDtoData.firstAttraction;
        doNothing().when(repository).deleteById(dto.getId());

        service.deleteById(dto.getId());

        verify(repository, times(1)).deleteById(dto.getId());
    }

    @Test
    void update() {
        AttractionEntity model = MockTestEntityData.updatedAttraction;
        AttractionDto expected = MockTestDtoData.updatedAttraction;

        when(repository.findById(expected.getId())).thenReturn(Optional.of(model));
        when(repository.save(model)).thenReturn(model);

        AttractionDto actual = service.update(MockTestDtoData.updatedAttraction, expected.getId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}