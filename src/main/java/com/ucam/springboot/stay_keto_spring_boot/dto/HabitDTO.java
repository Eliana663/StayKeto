package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;

@Data
public class HabitDTO {

    private Long id;
    private String name;
    private Long userId;
    private String color;


    public HabitDTO(Long id, String name, Long userId, String color) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.color = color;
    }
}
