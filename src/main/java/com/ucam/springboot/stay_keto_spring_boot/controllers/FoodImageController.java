package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.entities.FoodImage;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodImageRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FoodImageController {

    private final FoodImageRepository foodImageRepository;

    public FoodImageController(FoodImageRepository foodImageRepository) {
        this.foodImageRepository = foodImageRepository;
    }

    @GetMapping("/food-images")
    public List<FoodImage> listImages() {
        return foodImageRepository.findAll();
    }
}
