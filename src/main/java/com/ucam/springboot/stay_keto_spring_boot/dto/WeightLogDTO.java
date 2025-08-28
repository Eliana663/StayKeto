package com.ucam.springboot.stay_keto_spring_boot.dto;

import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Data


public class WeightLogDTO {

    private LocalDate date;
    private Double weight;
    private Long userId;

    public WeightLogDTO (LocalDate date, Double weight, Long userId) {

     this.date = date;
     this.weight = weight;
     this.userId = userId;

    }

}
