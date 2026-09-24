package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.ConsumptionDTO;
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
    private final IConsumptionService cS;
    private final IItemService iS;
    private final IRecipeService rS;
    private final ModelMapper modelMapper;

    public ConsumptionController(IConsumptionService cS, IItemService iS, IRecipeService rS, ModelMapper modelMapper) {
        this.cS = cS;
        this.iS = iS;
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ConsumptionDTO>> list() {

        List<ConsumptionDTO> lista = cS.list().stream()
                .map(historial -> {
                    ConsumptionDTO dto =
                            modelMapper.map(historial, ConsumptionDTO.class);

                    dto.setIdItem(historial.getItem().getIdItem());
                    dto.setIdReceta(historial.getRecipe().getIdRecipe());

                    return dto;
                }).toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ConsumptionDTO> insert(
            @Validated @RequestBody ConsumptionDTO dto) {

        Item item = iS.listid(dto.getIdItem())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item no encontrado"));

        Recipe recipe = rS.listId(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receta no encontrada"));

        Consumption historial =
                modelMapper.map(dto, Consumption.class);

        historial.setItem(item);
        historial.setRecipe(recipe);

        Consumption historialRegistrado =
                cS.insert(historial);

        ConsumptionDTO response =
                modelMapper.map(historialRegistrado, ConsumptionDTO.class);

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
    public ResponseEntity<ConsumptionDTO> listId(
            @PathVariable Long id) {

        Consumption historial = cS.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consumo no encontrado"));

        ConsumptionDTO dto =
                modelMapper.map(historial, ConsumptionDTO.class);

        dto.setIdItem(historial.getItem().getIdItem());
        dto.setIdReceta(historial.getRecipe().getIdRecipe());

        return ResponseEntity.ok(dto);
    }
}