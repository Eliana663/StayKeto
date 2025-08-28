package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.WeightLogDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.User;
import com.ucam.springboot.stay_keto_spring_boot.entities.WeightLog;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import com.ucam.springboot.stay_keto_spring_boot.repositories.WeightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GraphicsService {

    private final WeightLogRepository weightLogRepository;
    private final UserRepository userRepository;


    public GraphicsService(WeightLogRepository weightLogRepository, UserRepository userRepository) {
        this.weightLogRepository = weightLogRepository;
        this.userRepository = userRepository;
    }

    public WeightLog addWeitghtLog(WeightLogDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        WeightLog weightLog = new WeightLog();
        weightLog.setWeight(dto.getWeight());
        weightLog.setDate(LocalDate.now());
        weightLog.setUser(user);

        weightLogRepository.save(weightLog);

        //Updating actual weight
        user.setCurrentWeight(dto.getWeight());
        userRepository.save(user);

        return weightLog;
    }

}
