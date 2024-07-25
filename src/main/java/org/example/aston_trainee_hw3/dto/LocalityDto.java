package org.example.aston_trainee_hw3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocalityDto {

    private Long id;
    private String name;
    private Integer population;
    private Boolean hasMetro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String recommendation;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<AttractionDto> attractionEntities;


    private LocalityDto(LocalityDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.population = builder.population;
        this.hasMetro = builder.hasMetro;
        this.attractionEntities = builder.attractionEntities;
    }

    public static LocalityDtoBuilder builder() {
        return new LocalityDtoBuilder();
    }


    public static class LocalityDtoBuilder {
        private Long id;
        private String name;
        private Integer population;
        private List<AttractionDto> attractionEntities;
        private Boolean hasMetro;

        public LocalityDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public LocalityDtoBuilder name(String name) {
            this.name = name;
            return this;
        }
        public LocalityDtoBuilder population(Integer population) {
            this.population = population;
            return this;
        }

        public LocalityDtoBuilder hasMetro(boolean hasMetro) {
            this.hasMetro = hasMetro;
            return this;
        }

        public LocalityDtoBuilder attractionEntities(List<AttractionDto> attractionEntities) {
            this.attractionEntities = attractionEntities;
            return this;
        }

        public LocalityDto build() {
            return new LocalityDto(this);
        }
    }
}
