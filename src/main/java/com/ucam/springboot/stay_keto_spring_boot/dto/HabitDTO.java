package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class HabitDTO {

    private Long id;
    private String name;

    public HabitDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
