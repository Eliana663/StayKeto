package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface HabitTrackerRepository extends JpaRepository<HabitTracker, Long> {

    // Find all habits for a specific user
    List<HabitTracker> findByUserId(Long userId);

    // Find habits for a user on a specific date
    List<HabitTracker> findByUserIdAndDate(Long userId, LocalDate date);

    // Find all habits in a date range
    List<HabitTracker> findByDateBetween(LocalDate start, LocalDate end);

    // Find habits for a user in a date range
    List<HabitTracker> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

    // Check if a habit exists for a user on a specific date
    boolean existsByUserIdAndHabitNameAndDate(Long userId, String habitName, LocalDate date);

    // Find an existing habit by user, habit name, and date
    HabitTracker findByUserIdAndHabitNameAndDate(Long userId, String habitName, LocalDate date);

    }