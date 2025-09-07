package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.CalorieResponseDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import com.ucam.springboot.stay_keto_spring_boot.services.CaloriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calories")
public class CaloriesController {

    private final UserRepository userRepository;
    private final CaloriesService caloriesService;


    public CaloriesController(UserRepository userRepository, CaloriesService caloriesService) {
        this.caloriesService = caloriesService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CalorieResponseDTO> getDailyCalories (@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        CalorieResponseDTO dto = caloriesService.getCalorieRange(user);
        dto.setGoal(user.getGoal());
       return ResponseEntity.ok(dto);

    }
}
