package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.HabitTracker;
import com.ucam.springboot.stay_keto_spring_boot.repositories.HabitTrackerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitTrackerService {

    private final HabitTrackerRepository habitTrackerRepository;

    public HabitTrackerService(HabitTrackerRepository habitTrackerRepository) {
        this.habitTrackerRepository = habitTrackerRepository;
    }


    public HabitTracker saveHabit(HabitTracker habit) {
        return habitTrackerRepository.save(habit);
    }

    public List<HabitTracker> getHabitByUser(Long userId) {
        return habitTrackerRepository.findByUserId(userId);
    }

    public List<HabitTracker> getHabitsByUserAndDate(Long userId, LocalDate date) {
        return habitTrackerRepository.findByUserIdAndDate(userId, date);
    }

    public Optional<HabitTracker> getHabitById(Long id) {
        return habitTrackerRepository.findById(id);
    }

    public void deleteHabit(Long id) {
        habitTrackerRepository.deleteById(id);
    }


}