package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.*;
import pe.edu.upc.mikhuapp.entities.*;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecipeController {
    private final IRecipeService rS;
    private final ModelMapper modelMapper;
    private final ICountryService cS;
    private final IFamilyService fS;
    private final IIngredientService iS;

    public RecipeController(IRecipeService rS, ModelMapper modelMapper, ICountryService cS, IFamilyService fS, IIngredientService iS) {
        this.rS = rS;
        this.modelMapper = modelMapper;
        this.cS = cS;
        this.fS = fS;
        this.iS = iS;
    }

    //HU23 LISTAR RECETAS
    @GetMapping("/listarReceta")
    public ResponseEntity<List<RecipeDTOList>> listarReceta() {
        List<RecipeDTOList> lista = rS.list()
                .stream()
                .map(receta->modelMapper.map(receta, RecipeDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // HU11 REGISTRAR RECETA
    @PostMapping
    public ResponseEntity<RecipeDTOInsert> insertar(
            @Validated @RequestBody RecipeDTOInsert recipedto){

        Recipe recipe = modelMapper.map(recipedto, Recipe.class);

        rS.insert(recipe);

        RecipeDTOInsert responseDTO =
                modelMapper.map(recipe, RecipeDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recipe.getIdRecipe())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // HU13 ACTUALIZAR RECETA
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTOInsert> actualizarReceta(
            @PathVariable("id") Long id,
            @Validated @RequestBody RecipeDTOInsert dto){

        Recipe recipe = rS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));

        Recipe recipeactualizado = modelMapper.map(dto, Recipe.class);
        recipeactualizado.setIdRecipe(id);

        rS.update(recipeactualizado);

        RecipeDTOInsert responseDTO =
                modelMapper.map(recipeactualizado, RecipeDTOInsert.class);

        return ResponseEntity.ok(responseDTO);
    }

    // HU14 ELIMINAR RECETA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(
            @PathVariable("id") Long id){

        rS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));

        rS.delete(id);

        return ResponseEntity.noContent().build();
    }

    // HU15 CONSULTAR RECETA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTOList> listarId(
            @PathVariable("id") Long id){

        Recipe recipe = rS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada"));

        RecipeDTOList responseDTO =
                modelMapper.map(recipe, RecipeDTOList.class);

        return ResponseEntity.ok(responseDTO);
    }

    // HU57 CONSULTAR RECETAS POR INGREDIENTE
    @GetMapping("/ingrediente/{nombreIngrediente}")
    public ResponseEntity<List<RecipeDTOList>> listarPorIngrediente(
            @PathVariable("nombreIngrediente") String nombreIngrediente){

        iS.findByNomIngredient(nombreIngrediente)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        List<RecipeDTOList> lista = rS.listarPorIngrediente(nombreIngrediente)
                .stream()
                .map(receta -> modelMapper.map(receta, RecipeDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }
}
