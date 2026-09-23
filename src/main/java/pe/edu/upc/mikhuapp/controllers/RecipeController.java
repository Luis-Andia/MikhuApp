package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mikhuapp.dtos.RecipeDTOList;
import pe.edu.upc.mikhuapp.dtos.RecipeSuggestionDTO;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private IRecipeService recipeService;

    @Autowired
    private ModelMapper modelMapper;

    // HU23 LISTAR RECETAS
    @GetMapping
    public ResponseEntity<List<RecipeDTOList>> listRecipes() {

        List<RecipeDTOList> recipeList = recipeService.list()
                .stream()
                .map(recipe ->
                        modelMapper.map(recipe, RecipeDTOList.class))
                .toList();

        return ResponseEntity.ok(recipeList);
    }

    // HU22 CONSULTAR RECETAS PERSONALIZADAS
    @GetMapping("/personalized/{idFamily}")
    public ResponseEntity<List<RecipeSuggestionDTO>> findPersonalizedRecipes(
            @PathVariable Long idFamily) {

        List<RecipeSuggestionDTO> recipes =
                recipeService.findPersonalizedRecipes(idFamily);

        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No personalized recipes were found for family: "
                            + idFamily);
        }

        return ResponseEntity.ok(recipes);
    }
}