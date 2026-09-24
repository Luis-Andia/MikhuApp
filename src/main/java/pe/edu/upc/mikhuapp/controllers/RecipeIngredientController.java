package pe.edu.upc.mikhuapp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RecipeDTOInsert;
import pe.edu.upc.mikhuapp.dtos.RecipeIngredientDTO;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("hasRole('ADMIN')")
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

    // HU42: LISTAR INGREDIENTES-RECETA
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RecipeIngredientDTO>>list(){
        List<RecipeIngredientDTO> list = riS.list()
                .stream()
                .map(ri -> modelMapper.map(ri, RecipeIngredientDTO.class))
                .toList();
        return ResponseEntity.ok(list);
    }

    // HU43: ACTUALIZAR INGREDIENTE DE UNA RECETA
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecipeIngredientDTO> update(@Valid @RequestBody RecipeIngredientDTO dto){
        // Verificacion de existe la relacion
        Optional<RecipeIngredient> exists = riS.listId(dto.getIdRecipeIngredient());
        if (exists.isEmpty()) {
            throw new ResourceNotFoundException("No existe un ingrediente de receta con el id: " + dto.getIdRecipeIngredient());
        }

        // Verificar que el ingrediente y recta existan
        Optional<Ingredient> ingredient = iS.listId(dto.getIdIngredient());
        Optional<Recipe> recipe = rS.listId(dto.getIdRecipe());

        if (ingredient.isEmpty()) {
            throw new ResourceNotFoundException("No existe el ingrediente con el id: " + dto.getIdIngredient());
        }

        if (recipe.isEmpty()) {
            throw new ResourceNotFoundException("No existe la receta con el id: " + dto.getIdRecipe());
        }

        // Obtiene la relacion existente entre Receta e Ingrediente
        RecipeIngredient recipeIngredient = exists.get();

        // Actualiza los campos
        recipeIngredient.setRequestedQuantity(dto.getRequestedQuantity());

        // Añadir los objetos existentes
        recipeIngredient.setIngredient(ingredient.get());
        recipeIngredient.setRecipe(recipe.get());

        riS.update(recipeIngredient);
        RecipeIngredientDTO responseDTO = modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // HU44: ELIMINAR INGREDIENTE DE RECETA
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        RecipeIngredient recipeIngredient = riS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente de receta"));

        riS.delete(recipeIngredient.getIdRecipeIngredient());
        return ResponseEntity.noContent().build();
    }

    // HU45: CONSULTAR UN INGREDIENTE DE RECETA POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecipeIngredientDTO> getById(@PathVariable Long id){
        RecipeIngredient recipeIngredient = riS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente de receta"));

        RecipeIngredientDTO responseDTO = modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
        return ResponseEntity.ok(responseDTO);
    }
}
