package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.HistorialConsumoDTO;
import pe.edu.upc.mikhuapp.entities.Consumption;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IConsumptionService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/historial-consumo")
public class ConsumptionController {

    @Autowired
    private IConsumptionService historialConsumoService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IRecipeService recetaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<HistorialConsumoDTO>> list() {

        List<HistorialConsumoDTO> lista = historialConsumoService.list().stream()
                .map(historial -> {
                    HistorialConsumoDTO dto =
                            modelMapper.map(historial, HistorialConsumoDTO.class);

                    dto.setIdItem(historial.getItem().getIdItem());
                    dto.setIdReceta(historial.getRecipe().getIdRecipe());

                    return dto;
                }).toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<HistorialConsumoDTO> insert(
            @Validated @RequestBody HistorialConsumoDTO dto) {

        Item item = itemService.listid(dto.getIdItem())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item no encontrado"));

        Recipe recipe = recetaService.listid(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receta no encontrada"));

        Consumption historial =
                modelMapper.map(dto, Consumption.class);

        historial.setItem(item);
        historial.setRecipe(recipe);

        Consumption historialRegistrado =
                historialConsumoService.insert(historial);

        HistorialConsumoDTO response =
                modelMapper.map(historialRegistrado, HistorialConsumoDTO.class);

        response.setIdItem(item.getIdItem());
        response.setIdReceta(recipe.getIdRecipe());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(historialRegistrado.getIdConsumption())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialConsumoDTO> listId(
            @PathVariable Long id) {

        Consumption historial = historialConsumoService.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consumo no encontrado"));

        HistorialConsumoDTO dto =
                modelMapper.map(historial, HistorialConsumoDTO.class);

        dto.setIdItem(historial.getItem().getIdItem());
        dto.setIdReceta(historial.getRecipe().getIdRecipe());

        return ResponseEntity.ok(dto);
    }
}