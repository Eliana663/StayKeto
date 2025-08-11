package com.ucam.springboot.stay_keto_spring_boot.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class HabitTracker {

    private Long id;
    private Long userId;
    private String habitName;
    private Boolean completed;
    private LocalDate date;


}
