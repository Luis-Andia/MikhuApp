package pe.edu.upc.mikhuapp.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RecipeSuggestionDTO;
import pe.edu.upc.mikhuapp.exceptions.ExternalServiceException;

import java.net.URI;
import java.util.List;

@Component
public class SpoonacularClient {

    @Value("${spoonacular.api.url}")
    private String apiUrl;

    @Value("${spoonacular.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public List<RecipeSuggestionDTO> searchByIngredients(
            List<String> ingredients) {

        List<String> translatedIngredients = ingredients.stream()
                .map(this::translateIngredient)
                .toList();

        String ingredientsParameter =
                String.join(",", translatedIngredients);

        System.out.println(
                "Ingredients sent to Spoonacular: "
                        + ingredientsParameter);

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("apiKey", apiKey)
                .queryParam("ingredients", ingredientsParameter)
                .queryParam("number", 10)
                .queryParam("ranking", 1)
                .build()
                .encode()
                .toUri();

        try {

            List<RecipeSuggestionDTO> response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(
                            new ParameterizedTypeReference<
                                    List<RecipeSuggestionDTO>>() {
                            }
                    );

            if (response == null) {
                return List.of();
            }

            return response;

        } catch (RestClientException ex) {

            throw new ExternalServiceException(
                    "Could not query the external recipe service");
        }
    }

    private String translateIngredient(String ingredient) {

        if (ingredient == null) {
            return "";
        }

        return switch (ingredient.trim().toLowerCase()) {

            case "arroz" -> "rice";
            case "huevo", "huevos" -> "egg";
            case "pollo" -> "chicken";
            case "carne" -> "beef";
            case "papa", "papas" -> "potato";
            case "tomate", "tomates" -> "tomato";
            case "cebolla", "cebollas" -> "onion";
            case "ajo" -> "garlic";
            case "zanahoria", "zanahorias" -> "carrot";
            case "leche" -> "milk";
            case "queso" -> "cheese";
            case "pan" -> "bread";
            case "pasta" -> "pasta";
            case "atún", "atun" -> "tuna";
            case "limón", "limon" -> "lemon";
            case "palta", "aguacate" -> "avocado";
            case "plátano", "platano", "banana" -> "banana";

            default -> ingredient.trim();
        };
    }
}