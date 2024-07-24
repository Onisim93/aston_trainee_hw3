package org.example.aston_trainee_hw3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ServiceDto {
    private Long id;
    private String name;
    private String description;

    public ServiceDto(ServiceDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static ServiceDtoBuilder builder() {
        return new ServiceDtoBuilder();
    }

    public static class ServiceDtoBuilder {
        private Long id;
        private String name;
        private String description;


        public ServiceDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ServiceDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceDtoBuilder description(String description) {
            this.description = description;
            return this;
        }


        public ServiceDto build() {
            return new ServiceDto(this);
        }
    }
}
