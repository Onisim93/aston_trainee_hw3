package org.example.aston_trainee_hw3.service;

import org.example.aston_trainee_hw3.dto.AttractionDto;

import java.util.List;

public interface AttractionService extends BaseService<AttractionDto, Long> {

    List<AttractionDto> findByCriteria(String sortBy, String type, String localityName);
    List<AttractionDto> findByLocalityId(Long localityId);

}
