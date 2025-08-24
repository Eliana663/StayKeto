package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.MonthlyHabitsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.Habit;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.services.HabitService;
import com.ucam.springboot.stay_keto_spring_boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/api/habit")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping("/new-habit")
    public HabitDTO addNewHabit(@RequestBody HabitDTO habitDTO) {

        Long userId = habitDTO.getUserId();
        String habitName = habitDTO.getName();

        Habit habit = habitService.getHabitByName(habitDTO.getName(), habitDTO.getUserId());

        if (habit == null) {
            habit = new Habit();
            habit.setName(habitName);
            habit.setUserId(habitDTO.getUserId());
            habit = habitService.saveHabit(habit);
        }

        return new HabitDTO(habit.getId(), habit.getName(), userId);
    }



    // Save or erase habits(toggle)
    @PostMapping("/tracker/bulk-habits")
    public HabitTrackerDayDTO toggleHabit(@RequestBody HabitTrackerDTO habitTrackerDTO) {
        Long userId = habitTrackerDTO.getUserId();
        LocalDate date = habitTrackerDTO.getDate();
        Habit habit = habitTrackerDTO.getHabit();

        HabitTracker existing = habitService.getByUserIdAndDateAndHabit(userId, date, habit);
        HabitTracker newHabit = null;

        if (existing == null) {
                newHabit = new HabitTracker();
                newHabit.setUserId(userId);
                newHabit.setDate(date);
                newHabit.setHabit(habit);
                newHabit.setUserId(habitTrackerDTO.getUserId());
                existing = habitService.save(newHabit);

        } else {
            habitService.deleteById(existing.getId());
        }

        HabitTrackerDayDTO response = new HabitTrackerDayDTO();
        response.setUserId(userId);
        response.setDate(date);
        response.setCompletedHabits(List.of(new HabitDTO(habit.getId(), habit.getName(), userId)));

        return response;
    }


    // Obtener hábitos por mes
    @GetMapping("/tracker/month")
    public MonthlyHabitsDTO getHabitsForMonth(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return habitService.getMonthlyHabits(userId, year, month);
    }
 // Obtain all habits including new habits
 @GetMapping("/user/{userId}")
 public List<HabitDTO> getHabitsByUserId(@PathVariable Long userId) {
     return habitService.getHabitsByUserId(userId);
 }

    // Eliminar un hábito por id
    @DeleteMapping("/tracker/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteById(id);
    }
}
