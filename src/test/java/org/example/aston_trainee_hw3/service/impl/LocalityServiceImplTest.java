package org.example.aston_trainee_hw3.service.impl;

import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.MockTestEntityData;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.repository.LocalityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalityServiceImplTest {

    @Mock
    private LocalityRepository repository;

    @InjectMocks
    private LocalityServiceImpl service;

    @Test
    void findById() {
        LocalityDto expected = MockTestDtoData.firstLocality;
        LocalityEntity model = MockTestEntityData.firstLocality;
        when(repository.findById(expected.getId())).thenReturn(Optional.of(model));
        LocalityDto actual = service.findById(expected.getId());

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
        List<LocalityEntity> models = MockTestEntityData.allLocalities;
        List<LocalityDto> expected = MockTestDtoData.allLocalities;
        when(repository.findAll()).thenReturn(models);
        List<LocalityDto> actual = service.findAll();

        assertNotNull(actual);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void save() {
        LocalityEntity model = MockTestEntityData.newLocality;
        LocalityEntity modelCreated = MockTestEntityData.newLocalityCreated;

        LocalityDto newLocalityDto = MockTestDtoData.newLocality;
        LocalityDto expected = MockTestDtoData.newLocalityCreated;

        when(repository.save(model)).thenReturn(modelCreated);
        LocalityDto actual = service.save(newLocalityDto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        LocalityDto dto = MockTestDtoData.firstLocality;
        doNothing().when(repository).deleteById(dto.getId());

        service.deleteById(dto.getId());

        verify(repository, times(1)).deleteById(dto.getId());
    }

    @Test
    void update() {
        LocalityEntity model = MockTestEntityData.updatedLocality;
        LocalityDto expected = MockTestDtoData.updatedLocality;

        when(repository.findById(expected.getId())).thenReturn(Optional.of(model));
        when(repository.save(model)).thenReturn(model);

        LocalityDto actual = service.update(MockTestDtoData.updatedLocality, expected.getId());

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}