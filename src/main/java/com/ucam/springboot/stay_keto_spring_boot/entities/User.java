package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Integer age;
    private Double currentWeight;
    private Double targetWeight;
    private boolean pregnant;
    private String gender;
    private Integer height;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String activityLevel;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyLog> dailyLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodItems;


}
