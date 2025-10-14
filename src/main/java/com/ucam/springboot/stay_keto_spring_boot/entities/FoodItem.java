package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FoodItem {

    public FoodItem() {

    }
    public FoodItem(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String shortName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "image_id")
    private FoodImage image;
    private Double carbohydrates;
    private Double weightInGrams;
    private Double calories;
    private Double fat;
    private Double proteins;
    private Boolean isKeto;


    public Long getImageId() {
        return image != null ? image.getId() : null;
    }
    @Transient
    @JsonProperty("image_url")
    public String getImageUrl() {
        if (image == null || image.getImageUrl() == null) return null;

        String filename = image.getImageUrl(); // Ej: "images/Leche.jpg"


        if (filename.startsWith("http")) {

            return filename;
        } else {
            return "http://localhost:8081/" + filename;
        }
    }
    }
