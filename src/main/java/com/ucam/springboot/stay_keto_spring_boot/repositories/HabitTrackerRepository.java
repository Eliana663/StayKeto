package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HabitTrackerRepository extends JpaRepository<HabitTracker, Long> {

    List<HabitTracker> findByUserIdAndDate(Long userId, LocalDate date);

    HabitTracker findByUserIdAndDateAndHabit(Long userId, LocalDate date, Habit habit );



        @Query(value = "SELECT * FROM habit_tracker WHERE user_id = :userId AND YEAR(date) = :year AND MONTH(date) = :month;",
                    nativeQuery = true)
        List<HabitTracker> findByUserIdAndMonth(@Param("userId") Long userId,
                                                @Param("year") int year,
                                                @Param("month") int month);

    }