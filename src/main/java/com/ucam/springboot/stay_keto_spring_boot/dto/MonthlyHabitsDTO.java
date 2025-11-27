package com.ucam.springboot.stay_keto_spring_boot.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data

public class MonthlyHabitsDTO {
    private Long userId;
    private List<HabitTrackerDayDTO> monthlyTracker;
    private int year;
    private int month;

    public MonthlyHabitsDTO(Long userId, List<HabitTrackerDayDTO> monthlyTracker, int year, int month) {
        this.userId = userId;
        this.monthlyTracker = monthlyTracker;
        this.year = year;
        this.month = month;
    }

   }
