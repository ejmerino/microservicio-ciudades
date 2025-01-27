package com.espe.examen.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<CityTourist> cityTourists;
    private LocalDateTime createdAt;

    private String touristName;  // Agregamos un campo solo para almacenar el nombre del turista

    // Getters y setters

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    // Constructor que solo acepta ID
    public City(Long id) {
        this.id = id;
    }

    // Constructor vacío (si es necesario)
    public City() {}

    // Otros getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CityTourist> getCityTourists() {
        return cityTourists;
    }

    public void setCityTourists(Set<CityTourist> cityTourists) {
        this.cityTourists = cityTourists;
    }
}
