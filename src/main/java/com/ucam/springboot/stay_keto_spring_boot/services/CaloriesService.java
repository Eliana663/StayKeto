package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.CalorieResponseDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import org.springframework.stereotype.Service;

@Service
public class CaloriesService {
    public CalorieResponseDTO getCalorieRange(User user) {
        double bmr;
        if ("masculino".equalsIgnoreCase(user.getGender())) {
            bmr = 10 * user.getCurrentWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            bmr = 10 * user.getCurrentWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }

        double activityMultiplier = switch (user.getActivityLevel()) {
            case "Sedentario" -> 1.2;
            case "Ligero" -> 1.375;
            case "Moderado" -> 1.55;
            case "Activo" -> 1.725;
            default -> 1.2;
        };

        double maintenanceCalories = bmr * activityMultiplier;

        // Ajuste según objetivo

        CalorieResponseDTO dto = new CalorieResponseDTO();
        dto.setLoseWeightCalories(maintenanceCalories - 500);
        dto.setMaintenanceCalories(maintenanceCalories);
        dto.setGainMuscleCalories(maintenanceCalories + 300);

        return dto;
    }
}
