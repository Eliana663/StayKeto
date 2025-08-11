package com.ucam.springboot.stay_keto_spring_boot.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name= "habit_records")

public class HabitTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String habitName;
    private Boolean completed;
    private LocalDate date;


}
