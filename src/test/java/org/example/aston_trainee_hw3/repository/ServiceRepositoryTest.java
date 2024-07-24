package org.example.aston_trainee_hw3.repository;

import org.example.aston_trainee_hw3.MockTestEntityData;
import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository repository;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.1-alpine"))
            .withDatabaseName("testDb")
            .withUsername("test")
            .withPassword("test");

    @Test
    void findById() {
        ServiceEntity expected = MockTestEntityData.firstService;
        ServiceEntity actual = repository.findById(expected.getId()).get();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByNotExistsId() {
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(-1l).get());
    }

    @Test
    void findAll() {
        List<ServiceEntity> expected = MockTestEntityData.allServices;
        List<ServiceEntity> actual = repository.findAll();

        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void save() {
        ServiceEntity expected = MockTestEntityData.newServiceCreated;
        ServiceEntity actual = repository.save(MockTestEntityData.newService);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        repository.deleteById(MockTestEntityData.firstService.getId());
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(MockTestEntityData.firstService.getId()).get());
    }

    @Test
    void update() {
        ServiceEntity expected = MockTestEntityData.updatedService;
        ServiceEntity actual = repository.save(expected);
        Assertions.assertEquals(expected, actual);
    }
}
