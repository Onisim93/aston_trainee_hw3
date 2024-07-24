package org.example.aston_trainee_hw3;

import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.mapper.AttractionMapper;
import org.example.aston_trainee_hw3.mapper.LocalityMapper;
import org.example.aston_trainee_hw3.mapper.ServiceMapper;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.model.ServiceEntity;

import java.util.List;

public class MockTestDtoData {
    private MockTestDtoData() {}

    public static final LocalityDto firstLocality = LocalityMapper.INSTANCE.toDto(MockTestEntityData.firstLocality);
    public static final LocalityDto secondLocality = LocalityMapper.INSTANCE.toDto(MockTestEntityData.secondLocality);
    public static final LocalityDto newLocality = LocalityMapper.INSTANCE.toDto(MockTestEntityData.newLocality);
    public static final LocalityDto newLocalityCreated = LocalityMapper.INSTANCE.toDto(MockTestEntityData.newLocalityCreated);
    public static final LocalityDto updatedLocality = LocalityMapper.INSTANCE.toDto(MockTestEntityData.updatedLocality);
    public static final LocalityDto notValidLocality = LocalityMapper.INSTANCE.toDto(new LocalityEntity());


    public static final ServiceDto firstService = ServiceMapper.INSTANCE.toDto(MockTestEntityData.firstService);
    public static final ServiceDto secondService = ServiceMapper.INSTANCE.toDto(MockTestEntityData.secondService);
    public static final ServiceDto thirdService = ServiceMapper.INSTANCE.toDto(MockTestEntityData.thirdService);
    public static final ServiceDto newService = ServiceMapper.INSTANCE.toDto(MockTestEntityData.newService);
    public static final ServiceDto newServiceCreated = ServiceMapper.INSTANCE.toDto(MockTestEntityData.newServiceCreated);
    public static final ServiceDto updatedService = ServiceMapper.INSTANCE.toDto(MockTestEntityData.updatedService);
    public static final ServiceDto notValidService = ServiceMapper.INSTANCE.toDto(new ServiceEntity());

    public static final AttractionDto firstAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.firstAttraction);
    public static final AttractionDto secondAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.secondAttraction);
    public static final AttractionDto thirdAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.thirdAttraction);
    public static final AttractionDto fourthAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.fourthAttraction);
    public static final AttractionDto newAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.newAttraction);
    public static final AttractionDto newAttractionCreated = AttractionMapper.INSTANCE.toDto(MockTestEntityData.newAttractionCreated);
    public static final AttractionDto updatedAttraction = AttractionMapper.INSTANCE.toDto(MockTestEntityData.updatedAttraction);
    public static final AttractionDto notValidAttraction = AttractionMapper.INSTANCE.toDto(new AttractionEntity());

    public static final List<LocalityDto> allLocalities = LocalityMapper.INSTANCE.toDtoList(MockTestEntityData.allLocalities);
    public static final List<AttractionDto> allAttractions = AttractionMapper.INSTANCE.toDtoList(MockTestEntityData.allAttractions);
    public static final List<ServiceDto> allServices = ServiceMapper.INSTANCE.toDtoList(MockTestEntityData.allServices);

    public static final List<AttractionDto> museumAttractionsForSamara = AttractionMapper.INSTANCE.toDtoList(MockTestEntityData.museumAttractionsForSamara);
    public static final List<AttractionDto> samaraAttractions = AttractionMapper.INSTANCE.toDtoList(MockTestEntityData.samaraAttractions);
}
