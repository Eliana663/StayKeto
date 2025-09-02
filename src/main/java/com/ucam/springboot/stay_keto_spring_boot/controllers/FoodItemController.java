package com.ucam.springboot.stay_keto_spring_boot.controllers;



import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.ucam.springboot.stay_keto_spring_boot.services.FoodItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping(value = "/list")
    public List<FoodItem> getAllFoodItem() {
        return foodItemService.getAllFoodItems();
    }

    @Autowired
    private FoodItemRepository foodItemRepository;

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();

    }

    @GetMapping("/searchByID")
    public ResponseEntity<FoodItem> getFoodItemById(@RequestParam Long id) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(id);
        return foodItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/searchByName") // Implemented in FoodSearch component react
    public ResponseEntity<List<FoodItem>> searchFoodItems(@RequestParam String name) {
        List<FoodItem> results = foodItemRepository.findByNameContainingIgnoreCase(name);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodById(@PathVariable Long id) {
        Optional<FoodItem> optional = foodItemRepository.findById(id);
        return optional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

