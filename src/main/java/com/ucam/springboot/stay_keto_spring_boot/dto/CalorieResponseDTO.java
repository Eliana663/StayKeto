package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalorieResponseDTO {
    private double loseWeightCalories;
    private double maintenanceCalories;
    private double gainMuscleCalories;
    private String goal;

}
