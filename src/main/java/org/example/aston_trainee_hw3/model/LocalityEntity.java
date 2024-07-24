package org.example.aston_trainee_hw3.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a locality.
 */
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "locality")
public class LocalityEntity {

    /**
     * The identifier (sequence number) of the locality.
     */
    @Id
    @SequenceGenerator(name = "loc_seq", sequenceName = "locality_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loc_seq")
    private Long id;

    /**
     * The name of the locality.
     */
    @Column(nullable = false, updatable = false)
    private String name;

    /**
     * The population of the locality.
     */
    @Column(nullable = false)
    private Integer population;

    /**
     * The list of attractions in the locality.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "locality", cascade = {CascadeType.ALL})
    private List<AttractionEntity> attractionEntities;

    /**
     * Indicates whether the locality has a metro system.
     */
    @Column(nullable = false)
    private Boolean hasMetro;

    private LocalityEntity(LocalityBuilder localityBuilder) {
        this.id = localityBuilder.id;
        this.name = localityBuilder.name;
        this.population = localityBuilder.population;
        this.hasMetro = localityBuilder.hasMetro;
        this.attractionEntities = localityBuilder.attractionEntities;
    }

    public static LocalityBuilder builder() {
        return new LocalityBuilder();
    }

    public static class LocalityBuilder {
        private Long id;
        private String name;
        private Integer population;
        private Boolean hasMetro;
        private List<AttractionEntity> attractionEntities;

        public LocalityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LocalityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LocalityBuilder population(int population) {
            this.population = population;
            return this;
        }


        public LocalityBuilder hasMetro(boolean hasMetro) {
            this.hasMetro = hasMetro;
            return this;
        }

        public LocalityBuilder attractionEntities(List<AttractionEntity> attractionEntities) {
            this.attractionEntities = attractionEntities;
            return this;
        }

        public LocalityEntity build() {
            return new LocalityEntity(this);
        }
    }
}