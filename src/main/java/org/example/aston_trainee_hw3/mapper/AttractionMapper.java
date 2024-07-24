package org.example.aston_trainee_hw3.mapper;

import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.example.aston_trainee_hw3.model.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(imports = {LocalityMapper.class, ServiceMapper.class})
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    @Mapping(target = "locality.attractionEntities", ignore = true)
    AttractionDto toDto(AttractionEntity entity);

    @Mapping(source = "localityId", target = "locality")
    @Mapping(source = "serviceIds", target = "serviceEntities")
    AttractionEntity toEntity(AttractionDto dto);

    List<AttractionDto> toDtoList(List<AttractionEntity> entities);

    default LocalityEntity mapLocalityIdToLocality(Long localityId) {
        return LocalityEntity.builder().id(localityId).build();
    }
    default List<ServiceEntity> mapServiceIdsToServiceEntities(List<Long> serviceIds) {
        if (serviceIds == null || serviceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return serviceIds.stream().map(id -> ServiceEntity.builder().id(id).build()).toList();
    }
}
