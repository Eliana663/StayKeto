package com.ucam.springboot.stay_keto_spring_boot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Boolean isKeto;
    private int unit;
    private String unitType;
    private String name;
    private int Calories;
    private int fat;
    private int proteins;



}
