package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.MonthlyHabitsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.services.HabitTrackerService;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/habit-tracker")
public class HabitTrackerController {

    private final HabitTrackerService service;

    public HabitTrackerController(HabitTrackerService service) {
        this.service = service;
    }

    // Guardar o eliminar hábitos (toggle)
    @PostMapping("/bulk-habits")
    public HabitTrackerDayDTO toggleHabit(@RequestBody HabitTrackerDTO habitTrackerDTO) {
        Long userId = habitTrackerDTO.getUserId();
        LocalDate date = habitTrackerDTO.getDate();
        Habit habit = habitTrackerDTO.getHabit();

        HabitTracker existing = service.getByUserIdAndDateAndHabit(userId, date, habit);
        HabitTracker newHabit = null;

        if (existing == null) {
                newHabit = new HabitTracker();
                newHabit.setUserId(userId);
                newHabit.setDate(date);
                newHabit.setHabit(habit);

                existing = service.save(newHabit);

        } else {
                service.deleteById(existing.getId());
        }

        HabitTrackerDayDTO response = new HabitTrackerDayDTO();
        response.setUserId(userId);
        response.setDate(date);
        response.setCompletedHabits(List.of(new HabitDTO(habit.getId(), habit.getName())));

        return response;
    }
    // Obtener hábitos por mes
    @GetMapping("/month")
    public MonthlyHabitsDTO getHabitsForMonth(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return service.getMonthlyHabits(userId, year, month);
    }

    // Eliminar un hábito por id
    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        service.deleteById(id);
    }
}
