package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyLog;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.repositories.DailyLogRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;

    public DailyLogService(DailyLogRepository dailyLogRepository) {
        this.dailyLogRepository = dailyLogRepository;
    }

    public DailyLog saveDailyLog(DailyLog dailyLog) {
        return dailyLogRepository.save(dailyLog);
    }

    public Optional<DailyLog> findById(Long id) {
        return dailyLogRepository.findById(id);
    }

    public Optional<DailyLog> findByDateAndUser(LocalDate date, User user) {
        return dailyLogRepository.findByDateAndUser(date, user);
    }
}