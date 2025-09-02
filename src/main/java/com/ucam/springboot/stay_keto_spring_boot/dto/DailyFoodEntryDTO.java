package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyFoodEntryDTO {

    private Long foodItemId;
    private Long userId;
    private String name;
    private Double carbohydrates;
    private Double calories;
    private Double fat;
    private Double proteins;
    private Double weightInGrams;

    private LocalDate date;

    public DailyFoodEntryDTO (Long foodItemId, Long userId, String name, Double carbohydrates, Double calories, Double fat, Double proteins, Double weightInGrams, LocalDate date) {

        this.foodItemId = foodItemId;
        this.userId = userId;
        this.name = name;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.fat = fat;
        this.proteins = proteins;
        this.weightInGrams = weightInGrams;
        this.date = date;

    }


}

