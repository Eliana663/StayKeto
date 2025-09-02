package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.MonthlyHabitsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.repositories.HabitRepository;
import com.ucam.springboot.stay_keto_spring_boot.repositories.HabitTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;

@Service
public class HabitService {

    private final HabitTrackerRepository habitTrackerRepository;
    private final HabitRepository habitRepository;

    public HabitService(HabitTrackerRepository habitTrackerRepository, HabitRepository habitRepository) {
        this.habitTrackerRepository = habitTrackerRepository;
        this.habitRepository = habitRepository;
    }

    public Habit getHabitByNameAndUserIdAndColor(String name, Long userId, String color) {
        return habitRepository.findHabitByNameAndUserIdAndColor(name, userId, color);
    }

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    // --- Get habits per month ---

    public MonthlyHabitsDTO getMonthlyHabits(Long userId, int year, int month) {

        // --- Bring all habits per month
        List<HabitTracker> habits = habitTrackerRepository.findByUserIdAndMonth(userId, year, month);
        // ---  Group by day

        Map<LocalDate, List<HabitDTO>> habitsByDay = habits.stream()
                .collect(Collectors.groupingBy(
                        HabitTracker::getDate,
                        Collectors.mapping(h -> new HabitDTO(h.getId(), h.getHabit().getName(), userId, h.getHabit().getColor()),
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




    // --- Delete ---
    public void deleteById(Long id) {
        habitRepository.deleteById(id);
    }




    public HabitTracker getByUserIdAndDateAndHabit(Long userId, LocalDate date, Habit habit) {
        return habitTrackerRepository.findByUserIdAndDateAndHabit(userId, date, habit);

    }

    public List<HabitDTO> getHabitsByUserId(Long userId) {
        return habitRepository.findHabitsByUserId(userId).stream()
                .map(h -> new HabitDTO(h.getId(), h.getName(), userId, h.getColor()))
                .collect(Collectors.toList());
    }



}

