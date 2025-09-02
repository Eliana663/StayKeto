package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FoodImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private FoodItem foodItem;
    private String imageUrl;// Ex: "images/pollo.png


}
