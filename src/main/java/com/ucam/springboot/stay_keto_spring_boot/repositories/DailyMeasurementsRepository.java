package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyMeasurements;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DailyMeasurementsRepository extends JpaRepository<DailyMeasurements, Long> {

List<DailyMeasurements> findByUserIdOrderByDate (Long userId);
Optional<DailyMeasurements> findByUserIdAndDate (Long userId, LocalDate date);

}
