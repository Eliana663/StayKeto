package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.DailyMeasurementsDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.DailyMeasurements;
import com.ucam.springboot.stay_keto_spring_boot.repositories.DailyMeasurementsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyMeasurementsService {

    private final DailyMeasurementsRepository repository;

    public DailyMeasurementsService(DailyMeasurementsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DailyMeasurementsDTO saveEntry(DailyMeasurementsDTO dto) {

        Optional<DailyMeasurements> optional = repository.findByUserIdAndDate(dto.getUserId(), dto.getDate());
        DailyMeasurements existing;

        if (optional.isPresent()) {
            existing = optional.get();
            existing.setArm(dto.getArm());
            existing.setChest(dto.getChest());
            existing.setUnderBust(dto.getUnderBust());
            existing.setWaist(dto.getWaist());
            existing.setBelly(dto.getBelly());
            existing.setLeg(dto.getLeg());
        } else {
            existing = new DailyMeasurements();
            existing.setUserId(dto.getUserId());
            existing.setDate(dto.getDate());
            existing.setArm(dto.getArm());
            existing.setChest(dto.getChest());
            existing.setUnderBust(dto.getUnderBust());
            existing.setWaist(dto.getWaist());
            existing.setBelly(dto.getBelly());
            existing.setLeg(dto.getLeg());
        }

        System.out.println("Valores existentes en DB: " + existing.getArm() + ", " + existing.getChest() + "...");
        System.out.println("Valores a actualizar: " + dto.getArm() + ", " + dto.getChest() + "...");         DailyMeasurements saved = repository.saveAndFlush(existing);

        return new DailyMeasurementsDTO(
                saved.getId(),
                saved.getDate(),
                saved.getUserId(),
                saved.getArm(),
                saved.getChest(),
                saved.getUnderBust(),
                saved.getWaist(),
                saved.getBelly(),
                saved.getLeg()
        );
    }

    // Getting all measurements per user
    public List<DailyMeasurementsDTO> getAllMeasurementsByUser(Long userId) {
        return repository.findByUserIdOrderByDate(userId) // <
                .stream()
                .map(saved -> new DailyMeasurementsDTO(
                        saved.getId(),
                        saved.getDate(),
                        saved.getUserId(),
                        saved.getArm(),
                        saved.getChest(),
                        saved.getUnderBust(),
                        saved.getWaist(),
                        saved.getBelly(),
                        saved.getLeg()
                ))
                .collect(Collectors.toList());
    }

}
