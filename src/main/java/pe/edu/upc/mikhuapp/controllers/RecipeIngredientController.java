package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RecipeIngredientDTO;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.net.URI;

@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientController {
    private final IRecipeIngredientService riS;
    private final IIngredientService iS;
    private final IRecipeService rS;
    private final ModelMapper modelMapper;


    public RecipeIngredientController(IRecipeIngredientService riS, IIngredientService iS, IRecipeService rS, ModelMapper modelMapper) {
        this.riS = riS;
        this.iS = iS;
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    // Listar

    // HU41: REGISTRAR INGREDIENTE a RECETA
    @PostMapping
    public ResponseEntity<RecipeIngredientDTO> registrar(@RequestBody RecipeIngredientDTO dto) {
        // Validaciones
        Recipe recipe = rS.listId(dto.getIdRecipe())
                .orElseThrow(() -> new ResourceNotFoundException("No existe la receta con el id: " + dto.getIdRecipe()));
        Ingredient ingredient = iS.listId(dto.getIdIngredient())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente con el id: " + dto.getIdIngredient()));

        RecipeIngredient recipeIngredient = modelMapper.map(dto, RecipeIngredient.class);
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        riS.insert(recipeIngredient);

        RecipeIngredientDTO responseDTO = modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recipeIngredient.getIdRecipeIngredient())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // Buscar por ID
}
