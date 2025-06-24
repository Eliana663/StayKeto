package com.ucam.springboot.stay_keto_spring_boot.controllers;


import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/food")
public class FoodItemController {

    @GetMapping(value = "/list", produces = "application/json")
    public String getFoodList() {
        // Aquí deberías retornar una lista real desde un servicio o base de datos
        return "[\"Eggs\", \"Bacon\", \"Avocado\"]";
    }

}

//@RestController
//@RequestMapping("/api")
//public class HelloController {
//
//    @GetMapping("/prueba")
//    public String hello() {
//        return "Hello!";
//    }
//}