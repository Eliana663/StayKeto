package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.HabitDTO;
import com.ucam.springboot.stay_keto_spring_boot.dto.HabitTrackerDayDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.repositories.HabitTrackerRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitTrackerService {

    private final HabitTrackerRepository habitTrackerRepository;

    public HabitTrackerService(HabitTrackerRepository habitTrackerRepository) {
        this.habitTrackerRepository = habitTrackerRepository;
    }

    // --- Get habits per day -- //

    public HabitTrackerDayDTO getHabitsByDay(Long userId, LocalDate date) {
        List<HabitTracker> registers = habitTrackerRepository.findByUserIdAndDate(userId, date);

        List<HabitDTO> habits = registers.stream()
                .map(ht -> new HabitDTO(ht.getHabit().getId(), ht.getHabit().getName()))
                .collect(Collectors.toList());

        return new HabitTrackerDayDTO(userId, date.getDayOfMonth(), habits);
    }

    // --- Save or update ---
    public HabitTracker save(HabitTracker habit) {
        return habitTrackerRepository.save(habit);
    }

    public List<HabitTracker> saveAll(List<HabitTracker> habits) {
        return habitTrackerRepository.saveAll(habits);
    }

    // --- Get habits ---
    public List<HabitTracker> getHabitByUser(Long userId) {
        return habitTrackerRepository.findByUserId(userId);
    }

    public List<HabitTracker> getHabitsByUserAndDate(Long userId, LocalDate date) {
        return habitTrackerRepository.findByUserIdAndDate(userId, date);
    }

    public Optional<HabitTracker> getHabitById(Long id) {
        return habitTrackerRepository.findById(id);
    }

    public List<HabitTracker> getHabitsBetweenDates(LocalDate start, LocalDate end) {
        return habitTrackerRepository.findByDateBetween(start, end);
    }

    public List<HabitTracker> getHabitsBetweenDatesAndUser(LocalDate start, LocalDate end, Long userId) {
        return habitTrackerRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    // --- Check existence ---
    public boolean existsByUserDateAndName(Long userId, LocalDate date, String habitName) {
        return habitTrackerRepository.existsByUserIdAndHabitNameAndDate(userId, habitName, date);
    }

    // --- Find existing habit ---
    public HabitTracker findExistingHabit(Long userId, String habitName, LocalDate date) {
        return habitTrackerRepository.findByUserIdAndHabitNameAndDate(userId, habitName, date);
    }

    // --- Delete ---
    public void deleteById(Long id) {
        habitTrackerRepository.deleteById(id);
    }

    public void deleteByUserHabitAndDate(Long userId, String habitName, LocalDate date) {
        HabitTracker habit = findExistingHabit(userId, habitName, date);
        if (habit != null) {
            habitTrackerRepository.delete(habit);
        }
    }
}