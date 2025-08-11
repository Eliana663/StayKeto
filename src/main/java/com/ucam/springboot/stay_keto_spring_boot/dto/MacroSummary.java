package com.ucam.springboot.stay_keto_spring_boot.dto;

import java.time.LocalDate;

public class MacroSummary {

    private LocalDate date;
    private Double proteins;
    private Double fat;
    private Double carbohydrates;
    private Double calories;

    public MacroSummary(LocalDate date, Double proteins, Double fat, Double carbohydrates, Double calories) {
        this.date = date;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
    }

    // getters

    public LocalDate getDate() {
        return date;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getFat() {
        return fat;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public Double getCalories() {
        return calories;
    }
}