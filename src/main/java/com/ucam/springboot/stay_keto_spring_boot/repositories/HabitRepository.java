package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    Habit findHabitByNameAndUserId(String name, Long userId);

    @Query(
            value = "SELECT h.* FROM habit h " +
                    "WHERE h.user_id = :userId",
            nativeQuery = true
    )
    List<Habit> findHabitsByUserId(@Param("userId") Long userId);
}


