package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.WeightLogDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.entities.WeightLog;
import com.ucam.springboot.stay_keto_spring_boot.services.ChartsService;
import com.ucam.springboot.stay_keto_spring_boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/weight")
public class ChartsController {

    @Autowired
    private ChartsService chartsService;

    @Autowired
    private UserService userService;

    @PostMapping("users/{userId}/daily-weight")
    public ResponseEntity<WeightLog> addWeight(
            @PathVariable Long userId,
            @RequestBody WeightLogDTO dto) {
        dto.setUserId(userId);
        WeightLog saved = chartsService.addWeitghtLog(dto);
        return ResponseEntity.status(201).body(saved);
    }
    @PutMapping("users/{userId}/goal-weight")
    public ResponseEntity<User> updateGoalWeight(
            @PathVariable Long userId,
            @RequestBody Map<String, Double> body) {

        User updatedUser = userService.updateGoalWeight(userId, body.get("targetWeight"));
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("users/{userId}/daily-weight")
    public ResponseEntity<List<WeightLogDTO>> getDailyWeights(@PathVariable Long userId) {
        List<WeightLogDTO> weights = chartsService.getDailyWeights(userId);
        return ResponseEntity.ok(weights);
    }
    }
