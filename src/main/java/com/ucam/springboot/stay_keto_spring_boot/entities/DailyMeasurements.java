package com.ucam.springboot.stay_keto_spring_boot.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Table(
        name = "daily_measurements",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "date"})
)

public class DailyMeasurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @Column(name = "user_id")
    private Long userId;
    private Integer arm;
    private Integer chest;
    private Integer underBust;
    private Integer waist;
    private Integer belly;
    private Integer leg;

}
