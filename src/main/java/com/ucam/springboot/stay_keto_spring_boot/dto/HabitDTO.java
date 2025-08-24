package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class HabitDTO {

    private Long id;
    private String name;
    private Long userId;


    public HabitDTO(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
