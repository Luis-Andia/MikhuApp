package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.IngredientDTO;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredientController {

    @Autowired
    private IIngredientService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> list() {
        List<IngredientDTO> lista = ingredienteService.list().stream()
                .map(ingrediente -> modelMapper.map(ingrediente, IngredientDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> insert(@Validated @RequestBody IngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);

        Ingredient ingredientRegistrado = ingredienteService.insert(ingredient);

        IngredientDTO response = modelMapper.map(ingredientRegistrado, IngredientDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ingredientRegistrado.getIdIngrediente())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> listId(@PathVariable Long id) {

        Ingredient ingredient = ingredienteService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        IngredientDTO dto = modelMapper.map(ingredient, IngredientDTO.class);

        return ResponseEntity.ok(dto);
    }
}