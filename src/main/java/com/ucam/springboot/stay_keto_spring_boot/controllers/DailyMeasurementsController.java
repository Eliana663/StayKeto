package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.DailyMeasurementsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.DailyMeasurements;
import com.ucam.springboot.stay_keto_spring_boot.services.DailyMeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-measurements")
@CrossOrigin(origins = "http://localhost:5173")

public class DailyMeasurementsController {

    private final DailyMeasurementsService dailyMeasurementsService;

    @Autowired
    public DailyMeasurementsController(DailyMeasurementsService dailyMeasurementsService) {
        this.dailyMeasurementsService = dailyMeasurementsService;
    }

    // Saving day Measurements
    @PostMapping
    public DailyMeasurementsDTO saveDailyMeasurements(@RequestBody DailyMeasurementsDTO dto) {
        return dailyMeasurementsService.saveEntry(dto);
    }

    // et all user measurements
    @GetMapping("/user/{userId}")
    public List<DailyMeasurementsDTO> getMeasurementsByUser(@PathVariable Long userId) {
        return dailyMeasurementsService.getAllMeasurementsByUser(userId);
    }

}
