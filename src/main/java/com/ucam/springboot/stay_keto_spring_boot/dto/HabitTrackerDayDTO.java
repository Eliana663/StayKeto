package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class HabitTrackerDayDTO {


    private Long userId;
    private List<HabitDTO> completedHabits;
    private int day;

    public HabitTrackerDayDTO(Long userId, int day, List<HabitDTO> completedHabits) {
        this.userId = userId;
        this.completedHabits = completedHabits;
        this.day = day;

    }

}

