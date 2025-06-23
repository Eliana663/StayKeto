package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/auth")
public class FoodItemController {



    @GetMapping(value="/listar",
            produces="application/json"
    )
    @PreAuthorize("permitAll()")
    public List<String> listarFoodItems(){
        List<String> foodItems = new ArrayList<>();
        foodItems.add("Pera");
        foodItems.add("Manzana");
        foodItems.add("Mandarina");
        return foodItems;
    }

    @GetMapping("/hello-secured")
    public String helloSecured() { return "Hello World Secured"; }

    @GetMapping("/hello-secured2")
     public String helloSecured2() { return "Hello World Secured2"; }

    @GetMapping("/get")
    public String helloGet() {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    public String helloPost() {
        return "Hello World - POST>";
    }

    @PutMapping ("/put")
    public String helloPut() {
        return "Hello World - PUT>";
    }

    @DeleteMapping ("/delete")
    public String helloDelete() {
        return "Hello World - DELETE>";
    }

    @PatchMapping ("/patch")
    public String helloPatch() {
        return "Hello World - PATCH>";
    }


}

//
//@RestController
//@RequestMapping("/api")
//public class HelloController {
//
//    @GetMapping("/prueba")
//    public String hello() {
//        return "Hello!";
//    }
//}