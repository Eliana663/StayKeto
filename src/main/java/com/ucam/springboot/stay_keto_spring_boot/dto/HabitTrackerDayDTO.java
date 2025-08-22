package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class HabitTrackerDayDTO {
    private List<HabitDTO> completedHabits;
    private LocalDate date;
    private Long userId;

    public HabitTrackerDayDTO(Long userId, LocalDate date, List<HabitDTO> completedHabits) {
        this.completedHabits = completedHabits;
        this.date = date;
        this.userId = userId;

    }

}

