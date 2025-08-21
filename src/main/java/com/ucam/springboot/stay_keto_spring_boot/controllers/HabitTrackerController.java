package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.services.HabitTrackerService;
import lombok.Getter;
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

    @PostMapping("/bulk-habits")
    public List<HabitTracker> addOrUpdateHabits(@RequestBody List<HabitTracker> habits) {
        LocalDate today = LocalDate.now();
        List<HabitTracker> result = new ArrayList<>();

        for (HabitTracker habit : habits) {
            if (habit.getDate() == null) habit.setDate(today);

            HabitTracker existing = service.findExistingHabit(
                    habit.getUserId(),
                    habit.getHabit().getName(),
                    habit.getDate()
            );

            if (existing != null) {
                result.add(existing);
            } else {
                HabitTracker saved = service.save(habit);
                result.add(saved);
            }
        }

        return result;
    }

    @GetMapping("/month")
    public List<HabitTrackerDayDTO> getHabitsForMonth(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam Long userId) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Brings habittracker per user per month
        List<HabitTracker> habits = service.getHabitsBetweenDatesAndUser(start, end, userId);

        // Map: day -> habit list done
        Map<Integer, List<HabitDTO>> dayToHabits = new HashMap<>();

        for (HabitTracker ht : habits) {
            if (ht.getHabit() != null) { // validar que no sea null
                int day = ht.getDate().getDayOfMonth();
                dayToHabits.computeIfAbsent(day, k -> new ArrayList<>())
                        .add(new HabitDTO(ht.getHabit().getId(), ht.getHabit().getName()));
            }
        }

        // Convert maps to DTO list
        List<HabitTrackerDayDTO> response = new ArrayList<>();
        for (Map.Entry<Integer, List<HabitDTO>> entry : dayToHabits.entrySet()) {
            int day = entry.getKey();
            response.add(new HabitTrackerDayDTO(userId, day, entry.getValue()));
        }

        // Order by day
        response.sort(Comparator.comparing(HabitTrackerDayDTO::getDay));

        return response;
    }


    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        service.deleteById(id); // elimina directamente por el id del HabitTracker
    }

}
