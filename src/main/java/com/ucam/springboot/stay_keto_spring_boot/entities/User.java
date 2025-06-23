package com.ucam.springboot.stay_keto_spring_boot.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
    private Double currentWeight;
    private Double targetWeight;
    private boolean pregnant;

    @ManyToOne
    @JoinColumn(name = "dailyLog_id")
    private DailyLog dailyLog;

    @ManyToOne
    @JoinColumn(name = "foodItem_id")
    private FoodItem foodItem;

    public User(String username, String password, boolean enabled, boolean accountNoExpired, boolean accountNoLocked, boolean credentialNoExpired, List<SimpleGrantedAuthority> authorityList) {
    }
}
