package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity

public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate date;
    private Double weight;
    private float waistMeasurement;
    private float hipMeasurement;
    private float neck;
    private int dailyTotalCalories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
