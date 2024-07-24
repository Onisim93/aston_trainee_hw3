package org.example.aston_trainee_hw3.repository;

import org.example.aston_trainee_hw3.MockTestEntityData;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.service.specification.AttractionSpecification;
import org.example.aston_trainee_hw3.utils.SortUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
class AttractionRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.1-alpine"))
            .withDatabaseName("testDb")
            .withUsername("test")
            .withPassword("test");


    @Autowired
    private AttractionRepository repository;

    @Test
    void findById() {
        AttractionEntity expected = MockTestEntityData.firstAttraction;
        AttractionEntity actual = repository.findById(expected.getId()).get();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByNotExistsId() {
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(-1l).get());
    }

    @Test
    void findAll() {
        List<AttractionEntity> expected = MockTestEntityData.allAttractions;
        List<AttractionEntity> actual = repository.findAll();

        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void findByCriteria() {
        Specification<AttractionEntity> specs = AttractionSpecification.hasType("MUSEUM");
        specs = specs.and(AttractionSpecification.hasLocalityName("самара"));

        Sort sort = SortUtils.getSort("name::asc");

        List<AttractionEntity> expected = MockTestEntityData.museumAttractionsForSamara;
        List<AttractionEntity> actual = repository.findAll(specs, sort);

        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void findByLocalityId() {
        List<AttractionEntity> expected = MockTestEntityData.samaraAttractions;
        Long samaraId = expected.get(0).getId();

        List<AttractionEntity> actual = repository.findAllByLocalityIdOrderByName(samaraId);
        Assertions.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void save() {
        AttractionEntity expected = MockTestEntityData.newAttractionCreated;
        AttractionEntity actual = repository.save(MockTestEntityData.newAttraction);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        repository.deleteById(MockTestEntityData.firstAttraction.getId());
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(MockTestEntityData.firstAttraction.getId()).get());
    }

    @Test
    void update() {
        AttractionEntity expected = MockTestEntityData.updatedAttraction;
        AttractionEntity actual = repository.save(expected);
        Assertions.assertEquals(expected, actual);
    }
}