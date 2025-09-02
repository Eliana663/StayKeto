package com.ucam.springboot.stay_keto_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This is only by now, because i need to erase some images, this is Administrator profile
@Configuration
    public class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**") //for all routes
                            .allowedOrigins("http://localhost:5173") // URL frontend reacr
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                            .allowCredentials(true);
                }
            };
        }
    }

