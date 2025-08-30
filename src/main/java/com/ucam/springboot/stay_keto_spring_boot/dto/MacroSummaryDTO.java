package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MacroSummaryDTO {

    private LocalDate date;
    private Double proteins;
    private Double fat;
    private Double carbohydrates;
    private Double calories;

    public MacroSummaryDTO(LocalDate date, Double proteins, Double fat, Double carbohydrates, Double calories) {
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