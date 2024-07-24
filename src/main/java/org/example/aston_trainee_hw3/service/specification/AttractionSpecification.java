package org.example.aston_trainee_hw3.service.specification;

import jakarta.persistence.criteria.Join;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class AttractionSpecification {

    private AttractionSpecification() {}

    public static Specification<AttractionEntity> hasType(String type) {
        return ((root, query, criteriaBuilder) -> {
            if (type == null || type.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("type"), type.toUpperCase());
        });
    }

    public static Specification<AttractionEntity> hasLocalityName(String localityName) {
        return ((root, query, criteriaBuilder) -> {
            if (localityName == null || localityName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<AttractionEntity, LocalityEntity> localityJoin = root.join("locality");
            return criteriaBuilder.equal(localityJoin.get("name"), StringUtils.capitalizeFirstLetter(localityName));
        });
    }



}
