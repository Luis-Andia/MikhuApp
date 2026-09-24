package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final IIngredientService iS;
    private final ModelMapper modelMapper;

    public IngredientController(IIngredientService iS, ModelMapper modelMapper) {
        this.iS = iS;
        this.modelMapper = modelMapper;
    }

    // HU21: Registrar Ingrediente
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IngredientDTO> insert(@Validated @RequestBody IngredientDTO dto) {

        Ingredient ingredientRegistrado = iS.listId(dto.getIdIngredient())
                .orElseThrow(()->new ResourceNotFoundException("No existe el ingrediente con el id: " + dto.getIdIngredient()));
        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        iS.insert(ingredient);

        IngredientDTO responseDTO = modelMapper.map(ingredient, IngredientDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ingredientRegistrado.getIdIngredient())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    // HU22: Listar ingredientes registrados
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<IngredientDTO>> list() {
        List<IngredientDTO> lista = iS.list().stream()
                .map(ingredient -> modelMapper.map(ingredient, IngredientDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU23: Actualizar un ingrediente

    // HU24: Eliminar un ingrediente

    // HU25: Consultar un ingredinete por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IngredientDTO> listId(@PathVariable Long id) {

        Ingredient ingredient = iS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        IngredientDTO dto = modelMapper.map(ingredient, IngredientDTO.class);
        return ResponseEntity.ok(dto);
    }
}