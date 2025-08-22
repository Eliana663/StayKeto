package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.MonthlyHabitsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.repositories.HabitTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;

@Service
public class HabitTrackerService {

    private final HabitTrackerRepository habitTrackerRepository;

    public HabitTrackerService(HabitTrackerRepository habitTrackerRepository) {
        this.habitTrackerRepository = habitTrackerRepository;
    }

    // --- Get habits per month ---

    public MonthlyHabitsDTO getMonthlyHabits(Long userId, int year, int month) {

        // --- Bring all habits per month
        List<HabitTracker> habits = habitTrackerRepository.findByUserIdAndMonth(userId, year, month);
        // ---  Group by day

        Map<LocalDate, List<HabitDTO>> habitsByDay = habits.stream()
                .collect(Collectors.groupingBy(
                        HabitTracker::getDate,
                        Collectors.mapping(h -> new HabitDTO(h.getId(), h.getHabit().getName()),
                                Collectors.toList()
                        )
                ));

        List<HabitTrackerDayDTO> days = habitsByDay.entrySet().stream()
                .map(entry -> new HabitTrackerDayDTO(userId, entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(HabitTrackerDayDTO::getDate))
                .toList();
        return new MonthlyHabitsDTO(userId, days, year, month);
    }


    // --- Save or update ---
    public HabitTracker save(HabitTracker habit) {
        return habitTrackerRepository.save(habit);
    }

    public List<HabitTracker> getHabitsByUserIdAndDate(Long userId, LocalDate date) {
        return habitTrackerRepository.findByUserIdAndDate(userId, date);
    }

    public Habit getHabitById(Long id) {

        HabitTracker habitTracker = habitTrackerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + id));
        return habitTracker.getHabit();
    }

    // --- Delete ---
    public void deleteById(Long id) {
        habitTrackerRepository.deleteById(id);
    }

    public HabitTracker getByUserIdAndDateAndHabit(Long userId, LocalDate date, Habit habit) {
        return habitTrackerRepository.findByUserIdAndDateAndHabit(userId, date, habit);
    }


}

