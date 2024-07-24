package org.example.aston_trainee_hw3.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

/**
 * Represents a service (e.g., guide, car tour, etc.).
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "service")
public class ServiceEntity {

    /**
     * The identifier of the service.
     */
    @Id
    @SequenceGenerator(name = "service_seq", sequenceName = "service_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
    private Long id;

    /**
     * The name of the service.
     */
    private String name;

    /**
     * A brief description of the service.
     */
    private String description;

    /**
     * The list of attractions where the service is available.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @BatchSize(size = 20)
    @JoinTable(name = "attraction_services",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "attraction_id")
    )
    private List<AttractionEntity> attractions;

    private ServiceEntity(ServiceBuilder serviceBuilder) {
        this.id = serviceBuilder.id;
        this.name = serviceBuilder.name;
        this.description = serviceBuilder.description;
        this.attractions = serviceBuilder.attractions;
    }

    public static ServiceBuilder builder() {
        return new ServiceBuilder();
    }

    public static class ServiceBuilder {
        private Long id;
        private String name;
        private String description;
        private List<AttractionEntity> attractions;

        public ServiceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ServiceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ServiceBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ServiceBuilder attractions(List<AttractionEntity> attractions) {
            this.attractions = attractions;
            return this;
        }

        public ServiceEntity build() {
            return new ServiceEntity(this);
        }
    }
}
