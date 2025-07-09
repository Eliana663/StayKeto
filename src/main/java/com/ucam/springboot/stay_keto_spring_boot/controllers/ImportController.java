package com.ucam.springboot.stay_keto_spring_boot.controllers;

import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.services.OpenFoodFactsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/import")
@CrossOrigin(origins = "http://localhost:5173") // o el origen que uses en React
public class ImportController {

    private final OpenFoodFactsService openFoodFactsService;

    public ImportController(OpenFoodFactsService openFoodFactsService) {
        this.openFoodFactsService = openFoodFactsService;
    }

    // Importar por keyword (ejemplo: queso)
    @PostMapping("/byKeyword/{keyword}")
    public ResponseEntity<String> importByKeyword(@PathVariable String keyword) {
        int cantidad = openFoodFactsService.importProducts(keyword);
        return ResponseEntity.ok("Importación completada: " + cantidad + " productos guardados.");
    }

    // Importar masivamente (todas las páginas, máximo X páginas)
    @PostMapping("/all")
    public ResponseEntity<String> importarTodos() {
        int cantidad = openFoodFactsService.importAllProducts();
        return ResponseEntity.ok("Importación completa: " + cantidad + " productos guardados.");
    }
}


