package org.example.aston_trainee_hw3.mapper;

import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.model.AttractionEntity;
import org.example.aston_trainee_hw3.model.LocalityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocalityMapper {
    LocalityMapper INSTANCE = Mappers.getMapper(LocalityMapper.class);

    @Mapping(source = "attractionEntities", target = "attractionEntities")
    LocalityDto toDto(LocalityEntity entity);

    @Mapping(target = "attractionEntities", ignore = true)
    LocalityEntity toEntity(LocalityDto dto);

    List<LocalityDto> toDtoList(List<LocalityEntity> entities);

    default List<AttractionDto> mapAttractionEntities(List<AttractionEntity> entities) {
        return entities.stream().map(entity ->
                AttractionDto.builder()
                        .id(entity.getId())
                        .description(entity.getDescription())
                .name(entity.getName())
                .type(entity.getType())
                .creationDate(entity.getCreationDate())
                .build()).toList();
    }
}
