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
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IIngredientService ingredientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> list() {

        List<IngredientDTO> ingredientList = ingredientService.list().stream()
                .map(ingredient ->
                        modelMapper.map(ingredient, IngredientDTO.class))
                .toList();

        return ResponseEntity.ok(ingredientList);
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> insert(
            @Validated @RequestBody IngredientDTO dto) {

        Ingredient ingredient =
                modelMapper.map(dto, Ingredient.class);

        Ingredient registeredIngredient =
                ingredientService.insert(ingredient);

        IngredientDTO response =
                modelMapper.map(registeredIngredient, IngredientDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredIngredient.getIdIngredient())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> findById(
            @PathVariable Long id) {

        Ingredient ingredient = ingredientService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ingredient not found"));

        IngredientDTO dto =
                modelMapper.map(ingredient, IngredientDTO.class);

        return ResponseEntity.ok(dto);
    }
}