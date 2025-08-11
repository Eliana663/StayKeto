package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitTrackerRepository extends JpaRepository<HabitTracker, Long> {
    // Find all habits per user
    List<HabitTracker> findByUserId(Long userId);

    // Find habits of any  user in a Specific date
    List<HabitTracker> findByUserIdAndDate(Long userId, LocalDate date);


}
