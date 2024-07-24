package org.example.aston_trainee_hw3.mapper;

import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(imports = {AttractionMapper.class})
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDto toDto(ServiceEntity service);
    ServiceEntity toEntity(ServiceDto dto);

    List<ServiceDto> toDtoList(List<ServiceEntity> entities);
}
