package com.ucam.springboot.stay_keto_spring_boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DailyMeasurementsDTO {

    private Long id;
    private LocalDate date;
    private Long userId;
    private Integer arm;
    private Integer chest;
    private Integer underBust;
    private Integer waist;
    private Integer belly;
    private Integer leg;

    public DailyMeasurementsDTO (Long id, LocalDate date, Long userId, Integer arm, Integer chest, Integer underBust, Integer waist, Integer belly, Integer leg) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.arm = arm;
        this.chest = chest;
        this.underBust = underBust;
        this.waist = waist;
        this.belly = belly;
        this.leg = leg;

    }

}
