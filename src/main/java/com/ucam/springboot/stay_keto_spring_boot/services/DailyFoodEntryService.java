package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import com.ucam.springboot.stay_keto_spring_boot.repositories.DailyFoodEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyFoodEntryService {

        private final DailyFoodEntryRepository repository;

        public DailyFoodEntryService(DailyFoodEntryRepository repository) {
            this.repository = repository;

        }

        public DailyFoodEntry saveEntry(DailyFoodEntry entry) {
            return repository.save(entry);
        }

        public List<DailyFoodEntry> getEntriesByDate(LocalDate date) {
            return repository.findByDate(date);
        }

    }

