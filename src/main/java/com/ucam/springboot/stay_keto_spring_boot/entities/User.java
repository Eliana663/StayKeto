package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String profilePhoto;
    private String name;
    private String lastName;
    private String email;
    private Double currentWeight;
    private Double targetWeight;
    private boolean pregnant;
    private String Gender;
    private String height;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String activityLevel;




    @ManyToOne
    @JoinColumn(name = "dailyLog_id")
    private DailyLog dailyLog;

    @ManyToOne
    @JoinColumn(name = "foodItem_id")
    private FoodItem foodItem;


}
