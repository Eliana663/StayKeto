package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.services.HabitService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HabitService habitService;

    @Test
    void testDeleteHabit() throws Exception {
        Long habitId = 1L;

        mockMvc.perform(delete("/api/habit/delete/{id}", habitId))
                .andExpect(status().isOk());

        verify(habitService, times(1)).deleteById(habitId);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public HabitService habitService() {
            return Mockito.mock(HabitService.class);
        }
    }
}
