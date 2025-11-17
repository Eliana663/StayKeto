package com.ucam.springboot.stay_keto_spring_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Rutas públicas de imágenes (GET) sin credenciales
        registry.addMapping("/image-api/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET");

        // Resto de rutas de la API, permitiendo credenciales desde tu frontend
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:5173") // reemplaza por tu frontend real
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}
