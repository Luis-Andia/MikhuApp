package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.IngredienteDTO;
import pe.edu.upc.mikhuapp.entities.Ingrediente;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredienteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IIngredienteService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> list() {
        List<IngredienteDTO> lista = ingredienteService.list().stream()
                .map(ingrediente -> modelMapper.map(ingrediente, IngredienteDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> insert(@Validated @RequestBody IngredienteDTO dto) {

        Ingrediente ingrediente = modelMapper.map(dto, Ingrediente.class);

        Ingrediente ingredienteRegistrado = ingredienteService.insert(ingrediente);

        IngredienteDTO response = modelMapper.map(ingredienteRegistrado, IngredienteDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ingredienteRegistrado.getIdIngrediente())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> listId(@PathVariable Long id) {

        Ingrediente ingrediente = ingredienteService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        IngredienteDTO dto = modelMapper.map(ingrediente, IngredienteDTO.class);

        return ResponseEntity.ok(dto);
    }
}