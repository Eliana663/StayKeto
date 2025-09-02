package com.ucam.springboot.stay_keto_spring_boot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "habit")
@Data
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private String color;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<HabitTracker> trackers;



}
