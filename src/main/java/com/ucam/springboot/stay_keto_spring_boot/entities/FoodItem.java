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
    private String name;
    private String foodCalories;
    private Boolean isKeto;

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foodCalories='" + foodCalories + '\'' +
                ", isKeto=" + isKeto +
                '}';
    }
}
