package pe.edu.upc.mikhuapp.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RecetaSugeridaDTO;
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

    public List<RecetaSugeridaDTO> buscarPorIngredientes(List<String> ingredientes) {

        List<String> ingredientesTraducidos = ingredientes.stream()
                .map(this::traducirIngrediente)
                .toList();

        String ingredientesParametro =
                String.join(",", ingredientesTraducidos);

        System.out.println("Ingredientes enviados a Spoonacular: "
                + ingredientesParametro);

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("apiKey", apiKey)
                .queryParam("ingredients", ingredientesParametro)
                .queryParam("number", 10)
                .queryParam("ranking", 1)
                .build()
                .encode()
                .toUri();

        try {

            List<RecetaSugeridaDTO> response = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(
                            new org.springframework.core.ParameterizedTypeReference<
                                    List<RecetaSugeridaDTO>>() {
                            }
                    );

            if (response == null) {
                return List.of();
            }

            return response;

        } catch (RestClientException ex) {

            throw new ExternalServiceException(
                    "No se pudo consultar el servicio externo de recetas");
        }
    }

    private String traducirIngrediente(String ingrediente) {

        if (ingrediente == null) {
            return "";
        }

        return switch (ingrediente.trim().toLowerCase()) {

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

            default -> ingrediente.trim();
        };
    }
}