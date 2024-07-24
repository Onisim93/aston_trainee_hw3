package org.example.aston_trainee_hw3.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an attraction.
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "attraction")
public class AttractionEntity {

    /**
     * The identifier of the attraction.
     */
    @Id
    @SequenceGenerator(name = "att_seq", sequenceName = "attraction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "att_seq")
    private Long id;

    /**
     * The name of the attraction.
     */
    @Column(nullable = false, updatable = false)
    private String name;

    /**
     * The creation date of the attraction.
     */
    @Column(nullable = false, updatable = false)
    private LocalDate creationDate;

    /**
     * A brief description of the attraction.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The type of the attraction.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private AttractionType type;

    /**
     * The locality where the attraction is located.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locality_id", nullable = false)
    private LocalityEntity locality;

    /**
     * The list of services available at the attraction.
     */
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "attractions", cascade = {CascadeType.MERGE})
    @BatchSize(size = 10)
    private List<ServiceEntity> serviceEntities;

    private AttractionEntity(AttractionBuilder attractionBuilder) {
        this.id = attractionBuilder.id;
        this.name = attractionBuilder.name;
        this.creationDate = attractionBuilder.creationDate;
        this.description = attractionBuilder.description;
        this.type = attractionBuilder.type;
        this.locality = attractionBuilder.locality;
        this.serviceEntities = attractionBuilder.serviceEntities;
    }

    public static AttractionBuilder builder() {
        return new AttractionBuilder();
    }

    public static class AttractionBuilder {
        private Long id;
        private String name;
        private LocalDate creationDate;
        private String description;
        private AttractionType type;
        private LocalityEntity locality;
        private List<ServiceEntity> serviceEntities;

        public AttractionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AttractionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AttractionBuilder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AttractionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public AttractionBuilder type(AttractionType type) {
            this.type = type;
            return this;
        }

        public AttractionBuilder locality(LocalityEntity locality) {
            this.locality = locality;
            return this;
        }

        public AttractionBuilder serviceEntities(List<ServiceEntity> serviceEntities) {
            this.serviceEntities = serviceEntities;
            return this;
        }

        public AttractionEntity build() {
            return new AttractionEntity(this);
        }
    }
}
