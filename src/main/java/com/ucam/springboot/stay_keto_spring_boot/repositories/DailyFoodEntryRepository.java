package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyFoodEntryRepository extends JpaRepository<DailyFoodEntry, Long> {
    List<DailyFoodEntry> findByDate(LocalDate date);
    List<DailyFoodEntry> findByDateBetween(LocalDate start, LocalDate end);
}
