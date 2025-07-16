package com.ucam.springboot.stay_keto_spring_boot.repositories;

import com.ucam.springboot.stay_keto_spring_boot.entities.FoodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface FoodImageRepository extends JpaRepository<FoodImage, Long> {
        // Aquí puedes agregar consultas personalizadas si las necesitas,
        // pero por ahora no hace falta añadir nada más.
    }

