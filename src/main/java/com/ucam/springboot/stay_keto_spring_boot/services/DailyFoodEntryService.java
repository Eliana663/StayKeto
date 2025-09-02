package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.dto.DailyFoodEntryDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import com.ucam.springboot.stay_keto_spring_boot.dto.MacroSummaryDTO;
import com.ucam.springboot.stay_keto_spring_boot.repositories.DailyFoodEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyFoodEntryService {

        private final DailyFoodEntryRepository repository;

        public DailyFoodEntryService(DailyFoodEntryRepository repository) {

            this.repository = repository;

        }

        public DailyFoodEntryDTO saveEntry(DailyFoodEntry dto) {
            DailyFoodEntry entry = new DailyFoodEntry();
            entry.setFoodItemId(dto.getFoodItemId());
            entry.setUserId(dto.getUserId());
            entry.setName(dto.getName());
            entry.setCarbohydrates(dto.getCarbohydrates());
            entry.setCalories(dto.getCalories());
            entry.setFat(dto.getFat());
            entry.setProteins(dto.getProteins());
            entry.setWeightInGrams(dto.getWeightInGrams());
            entry.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());


            DailyFoodEntry saved = repository.save(entry);

            return new DailyFoodEntryDTO(
                    saved.getFoodItemId(),
                    saved.getUserId(),
                    saved.getName(),
                    saved.getCarbohydrates(),
                    saved.getCalories(),
                    saved.getFat(),
                    saved.getProteins(),
                    saved.getWeightInGrams(),
                    saved.getDate()


            );
        }

        public List<DailyFoodEntryDTO> getEntriesByDate(LocalDate date) {
            return repository.findByDate(date)
            .stream()
            .map(entry -> new DailyFoodEntryDTO(
                    entry.getFoodItemId(),
                    entry.getUserId(),
                    entry.getName(),
                    entry.getCarbohydrates(),
                    entry.getCalories(),
                    entry.getFat(),
                    entry.getProteins(),
                    entry.getWeightInGrams(),
                    entry.getDate()
            ))
                    .collect(Collectors.toList());
        }

    public List<MacroSummaryDTO> getMacrosGroupedByDate(LocalDate start, LocalDate end) {
        List<DailyFoodEntry> entries = repository.findByDateBetween(start, end);

        return entries.stream()
                .collect(Collectors.groupingBy(DailyFoodEntry::getDate))
                .entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<DailyFoodEntry> dayEntries = entry.getValue();

                    Double proteins = dayEntries.stream().mapToDouble(DailyFoodEntry::getProteins).sum();
                    Double fat = dayEntries.stream().mapToDouble(DailyFoodEntry::getFat).sum();
                    Double carbohydrates = dayEntries.stream().mapToDouble(DailyFoodEntry::getCarbohydrates).sum();
                    Double calories = dayEntries.stream().mapToDouble(DailyFoodEntry::getCalories).sum();

                    return new MacroSummaryDTO(date, proteins, fat, carbohydrates, calories);
                })
                .sorted(Comparator.comparing(MacroSummaryDTO::getDate))
                .collect(Collectors.toList());
    }

    }

