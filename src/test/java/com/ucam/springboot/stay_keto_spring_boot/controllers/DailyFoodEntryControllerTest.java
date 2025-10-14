package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.dto.DailyFoodEntryDTO;
import com.ucam.springboot.stay_keto_spring_boot.entities.DailyFoodEntry;
import com.ucam.springboot.stay_keto_spring_boot.services.DailyFoodEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DailyFoodEntryControllerTest {

    private DailyFoodEntryService service;
    private DailyFoodEntryController controller;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(DailyFoodEntryService.class);
        controller = new DailyFoodEntryController(service);
    }

    @Test
    void testGetFoodEntriesByDate() {
        // Creamos entities de ejemplo
        DailyFoodEntry entry1 = new DailyFoodEntry();
        entry1.setId(1L);
        entry1.setName("Eggs");
        entry1.setUserId(10L);
        entry1.setDate(LocalDate.parse("2025-09-24"));

        DailyFoodEntry entry2 = new DailyFoodEntry();
        entry2.setId(2L);
        entry2.setName("Bacon");
        entry2.setUserId(10L);
        entry2.setDate(LocalDate.parse("2025-09-24"));

        List<DailyFoodEntry> mockList = List.of(entry1, entry2);


        when(service.getEntriesByDate(LocalDate.parse("2025-09-24")))
                .thenReturn(mockList.stream().map(e -> {
                    DailyFoodEntryDTO dto = new DailyFoodEntryDTO();
                    dto.setId(e.getId());
                    dto.setName(e.getName());
                    return dto;
                }).toList());


        List<DailyFoodEntryDTO> response = controller.getFoodEntriesByDate("2025-09-24");

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("Eggs", response.get(0).getName());
        assertEquals("Bacon", response.get(1).getName());

        verify(service, times(1)).getEntriesByDate(LocalDate.parse("2025-09-24"));
    }
}
