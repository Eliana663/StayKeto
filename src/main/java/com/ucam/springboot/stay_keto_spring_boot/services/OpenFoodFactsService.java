package com.ucam.springboot.stay_keto_spring_boot.services;

import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodItemRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class OpenFoodFactsService {

    private final RestTemplate restTemplate;
    private final FoodItemRepository foodItemRepository;

    public OpenFoodFactsService(RestTemplateBuilder builder, FoodItemRepository repo) {
        this.restTemplate = builder.build();
        this.foodItemRepository = repo;
    }

    /**
     * Importar productos por palabra clave (ej: "queso")
     */
    public int importProducts(String keyword) {
        String url = "https://world.openfoodfacts.org/cgi/search.pl?search_terms="
                + keyword + "&search_simple=1&action=process&json=1";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> products = (List<Map<String, Object>>) response.getBody().get("products");

        return saveProducts(products);
    }

    /**
     * Importar todos los productos disponibles desde OpenFoodFacts
     */
    public int importAllProducts() {
        int totalCount = 0;
        int page = 1;
        int maxPerPage = 1000;

        while (true) {
            String url = "https://world.openfoodfacts.org/cgi/search.pl?action=process&json=1&page="
                    + page + "&page_size=" + maxPerPage;

            System.out.println("Descargando página " + page + "...");

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            List<Map<String, Object>> products = (List<Map<String, Object>>) response.getBody().get("products");

            if (products == null || products.isEmpty()) {
                System.out.println("No hay más productos en la página " + page);
                break;
            }

            int saved = saveProducts(products);
            totalCount += saved;

            System.out.println("Página " + page + " procesada. Productos guardados: " + saved);

            page++;

            // Seguridad: no pasar de 200 páginas (~200.000 productos)
            if (page > 200) {
                System.out.println("Límite de seguridad alcanzado. Deteniendo importación.");
                break;
            }

            // (Opcional) Pausa para no saturar el servidor
            try {
                Thread.sleep(500); // medio segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return totalCount;
    }

    /**
     * Guarda una lista de productos validando los campos mínimos.
     */
    private int saveProducts(List<Map<String, Object>> products) {
        int count = 0;

        for (Map<String, Object> product : products) {
            try {
                String productName = (String) product.get("product_name");
                Map<String, Object> nutriments = (Map<String, Object>) product.get("nutriments");

                // Validaciones básicas
                if (productName == null || productName.trim().isEmpty()) continue;
                if (nutriments == null) continue;

                Double carbs = castDouble(nutriments.get("carbohydrates_100g"));
                Double calories = castDouble(nutriments.get("energy-kcal_100g"));

                if ((carbs == null || carbs == 0) && (calories == null || calories == 0)) continue;

                FoodItem item = new FoodItem();
                item.setName(productName);
                item.setTitle((String) product.get("brands"));
                item.setPhoto((String) product.get("image_front_url"));
                item.setQuantity((String) product.get("quantity"));
                item.setCommonName((String) product.get("generic_name"));

                item.setFat(castDouble(nutriments.get("fat_100g")));
                item.setCarbohydrates(carbs);
                item.setProteins(castDouble(nutriments.get("proteins_100g")));
                item.setCalories(calories);

                item.setIsKeto(carbs != null && carbs < 5.0);

                foodItemRepository.save(item);
                count++;

            } catch (Exception e) {
                System.out.println("Error guardando producto: " + e.getMessage());
            }
        }

        return count;
    }

    /**
     * Convierte un valor a Double, sea número o string.
     */
    private Double castDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return null;
    }

    private Double defaultDouble(Double value) {
        return value != null ? value : 0.0;
    }
}