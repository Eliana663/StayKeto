package com.ucam.springboot.stay_keto_spring_boot.repositories;


import com.ucam.springboot.stay_keto_spring_boot.entities.FoodImage;
import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Optional<FoodItem> findByNameIgnoreCase(String name);

    List<FoodItem> findByNameContainingIgnoreCase(String name);

    FoodItem findByImage(FoodImage foodImage);
}
