package com.ucam.springboot.stay_keto_spring_boot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Table(name = "daily_food_entries")
@Getter
@Setter
public class DailyFoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long foodItemId;
    private String name;

    private Double carbohydrates;
    private Double calories;
    private Double fat;
    private Double proteins;
    private Double weightInGrams;

    private LocalDate date;

}
