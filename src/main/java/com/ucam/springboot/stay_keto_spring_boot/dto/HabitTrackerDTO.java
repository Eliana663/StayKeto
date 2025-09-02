package com.ucam.springboot.stay_keto_spring_boot.dto;

import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class HabitTrackerDTO {

    private Long userId;
    private LocalDate date;
    private Habit habit;


    public HabitTrackerDTO(Long userId, LocalDate date, Habit habit) {
        this.userId = userId;
        this.date = date;
        this.habit = habit;
    }
}
