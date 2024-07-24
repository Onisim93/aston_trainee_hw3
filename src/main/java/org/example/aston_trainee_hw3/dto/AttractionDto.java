package org.example.aston_trainee_hw3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.aston_trainee_hw3.model.AttractionType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttractionDto {

    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    private String description;
    private AttractionType type;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalityDto locality;

    private Long localityId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ServiceDto> serviceEntities;

    private List<Long> serviceIds;

    private AttractionDto(AttractionDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.creationDate = builder.creationDate;
        this.description = builder.description;
        this.type = builder.type;
        this.serviceIds = builder.serviceIds;
        this.localityId = builder.localityId;
        this.locality = builder.locality;
        this.serviceEntities = builder.serviceEntities;
    }

    public static AttractionDtoBuilder builder() {
        return new AttractionDtoBuilder();
    }


    public static class AttractionDtoBuilder {
        private Long id;
        private String name;
        private LocalDate creationDate;
        private String description;
        private AttractionType type;
        private Long localityId;
        private LocalityDto locality;
        private List<ServiceDto> serviceEntities;
        private List<Long> serviceIds;


        public AttractionDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AttractionDtoBuilder localityId(Long localityId) {
            this.localityId = localityId;
            return this;
        }

        public AttractionDtoBuilder serviceIds(List<Long> serviceIds) {
            this.serviceIds = serviceIds;
            return this;
        }

        public AttractionDtoBuilder name(String name) {
            this.name = name;
            return this;
        }
        public AttractionDtoBuilder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }
        public AttractionDtoBuilder description(String description) {
            this.description = description;
            return this;
        }
        public AttractionDtoBuilder type(AttractionType type) {
            this.type = type;
            return this;
        }
        public AttractionDtoBuilder locality(LocalityDto locality) {
            this.locality = locality;
            return this;
        }
        public AttractionDtoBuilder serviceEntities(List<ServiceDto> serviceEntities) {
            this.serviceEntities = serviceEntities;
            return this;
        }
        public AttractionDto build() {
            return new AttractionDto(this);
        }
    }
}
