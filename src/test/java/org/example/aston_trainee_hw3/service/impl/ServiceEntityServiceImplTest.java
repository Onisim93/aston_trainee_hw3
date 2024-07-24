package org.example.aston_trainee_hw3.service.impl;

import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.MockTestEntityData;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.exception.EntityNotFoundException;
import org.example.aston_trainee_hw3.exception.InvalidEntityException;
import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.example.aston_trainee_hw3.repository.ServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceEntityServiceImplTest {

    @Mock
    private ServiceRepository repository;

    @InjectMocks
    private ServiceEntityServiceImpl service;

    @Test
    void findById() {
        ServiceDto expected = MockTestDtoData.firstService;
        ServiceEntity model = MockTestEntityData.firstService;
        when(repository.findById(expected.getId())).thenReturn(Optional.of(model));
        ServiceDto actual = service.findById(expected.getId());

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
        List<ServiceEntity> models = MockTestEntityData.allServices;
        List<ServiceDto> expected = MockTestDtoData.allServices;
        when(repository.findAll()).thenReturn(models);
        List<ServiceDto> actual = service.findAll();


        assertNotNull(actual);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

    }

    @Test
    void save() {
        ServiceEntity model = MockTestEntityData.newService;
        ServiceEntity modelCreated = MockTestEntityData.newServiceCreated;

        ServiceDto newServiceDto = MockTestDtoData.newService;
        ServiceDto expected = MockTestDtoData.newServiceCreated;

        when(repository.save(model)).thenReturn(modelCreated);
        ServiceDto actual = service.save(newServiceDto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void saveNotValidEntity() {
        ServiceDto notValidServiceDto = MockTestDtoData.notValidService;
        assertThrows(InvalidEntityException.class, () -> service.save(notValidServiceDto));
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById() {
        ServiceDto dto = MockTestDtoData.firstService;
        doNothing().when(repository).deleteById(dto.getId());

        service.deleteById(dto.getId());

        verify(repository, times(1)).deleteById(dto.getId());
    }

    @Test
    void update() {
        ServiceEntity model = MockTestEntityData.updatedService;
        ServiceDto expected = MockTestDtoData.updatedService;

        when(repository.save(model)).thenReturn(model);


        ServiceDto actual = service.update(MockTestDtoData.updatedService, expected.getId());
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}