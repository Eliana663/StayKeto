package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyLog;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

public interface DailyLogService {

    DailyLog saveDailyLog(DailyLog dailyLog);

    Optional<DailyLog> findById(Long id);

    Optional<DailyLog> findByDateAndUser(LocalDate date, User user);

}
