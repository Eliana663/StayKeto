package com.ucam.springboot.stay_keto_spring_boot.controllers;
import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import com.ucam.springboot.stay_keto_spring_boot.dto.MacroSummary;
import com.ucam.springboot.stay_keto_spring_boot.services.DailyFoodEntryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-food")
public class DailyFoodEntryController {

    private final DailyFoodEntryService service;

    public DailyFoodEntryController(DailyFoodEntryService service) {
        this.service = service;
    }

    @PostMapping
    public DailyFoodEntry addFoodEntry(@RequestBody DailyFoodEntry entry) {
        if(entry.getDate() == null) {
            entry.setDate(LocalDate.now());
        }
        return service.saveEntry(entry);
}

    @GetMapping
    public List<DailyFoodEntry> getFoodEntriesByDate(@RequestParam String date) {
    LocalDate localDate = LocalDate.parse(date); // yyyy-MM-DD format
    return service.getEntriesByDate(localDate);
    }

    @GetMapping("/macros-by-date")
    public List<MacroSummary> getMacrosByDateRange(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return service.getMacrosGroupedByDate(startDate, endDate);
    }

}
