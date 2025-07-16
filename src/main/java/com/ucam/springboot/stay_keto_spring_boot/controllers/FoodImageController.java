package com.ucam.springboot.stay_keto_spring_boot.controllers;


import com.ucam.springboot.stay_keto_spring_boot.entities.FoodImage;
import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodImageRepository;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class FoodImageController {

    private final FoodImageRepository foodImageRepository;
    private final FoodItemRepository foodItemRepository;

    public FoodImageController(FoodImageRepository foodImageRepository, FoodItemRepository foodItemRepository) {
        this.foodImageRepository = foodImageRepository;
        this.foodItemRepository = foodItemRepository;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        Optional<FoodImage> optionalImage = foodImageRepository.findById(id);
        if (optionalImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        FoodImage image = optionalImage.get();


        // Delete physical file
        String path = "src/main/resources/static" + image.getImageUrl();
        File file = new File(path);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("No se pudo borrar el archivo físico");
            }
        }

        // Clean relation with FoodItem
        FoodItem foodItem = foodItemRepository.findByImage(image);
        if (foodItem != null) {
            foodItem.setImage(null);
            foodItemRepository.save(foodItem);
        }

        //Clean image file
        foodImageRepository.delete(image);

        return ResponseEntity.ok("Imagen eliminada y relación limpiada");
    }


    @GetMapping
    public List<FoodImage> listImages() {
        return foodImageRepository.findAll();
    }
}