package com.ucam.springboot.stay_keto_spring_boot.repositories;


import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    Optional<FoodItem> findByNameIgnoreCase(String name);




}
