package com.ucam.springboot.stay_keto_spring_boot.services;

import org.springframework.stereotype.Service;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodItemRepository;
import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }
}
