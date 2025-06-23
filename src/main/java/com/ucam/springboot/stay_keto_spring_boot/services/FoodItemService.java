package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.DailyLog;
import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;

import java.util.List;
import java.util.Optional;

public interface FoodItemService {

    FoodItem saveFoodItem ( FoodItem foodItem);

    Optional<FoodItem> findById(Long id);

    List<FoodItem> listAllFoodItems();


}
