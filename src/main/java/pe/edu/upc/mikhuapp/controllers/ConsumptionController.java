package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.ConsumptionDTO;
import pe.edu.upc.mikhuapp.dtos.ConsumptionHistoryResponseDTO;
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
@RequestMapping("/consumption-history")
public class ConsumptionController {

    @Autowired
    private IConsumptionService consumptionService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IRecipeService recipeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ConsumptionDTO>> list() {

        List<ConsumptionDTO> consumptionList = consumptionService.list().stream()
                .map(consumption -> {
                    ConsumptionDTO dto =
                            modelMapper.map(consumption, ConsumptionDTO.class);

                    dto.setIdItem(consumption.getItem().getIdItem());
                    dto.setIdRecipe(consumption.getRecipe().getIdRecipe());

                    return dto;
                }).toList();

        return ResponseEntity.ok(consumptionList);
    }

    @PostMapping
    public ResponseEntity<ConsumptionDTO> insert(
            @Validated @RequestBody ConsumptionDTO dto) {

        Item item = itemService.findById(dto.getIdItem())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found"));

        Recipe recipe = recipeService.findById(dto.getIdRecipe())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recipe not found"));

        Consumption consumption =
                modelMapper.map(dto, Consumption.class);

        consumption.setItem(item);
        consumption.setRecipe(recipe);

        Consumption registeredConsumption =
                consumptionService.insert(consumption);

        ConsumptionDTO response =
                modelMapper.map(registeredConsumption, ConsumptionDTO.class);

        response.setIdItem(item.getIdItem());
        response.setIdRecipe(recipe.getIdRecipe());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredConsumption.getIdConsumption())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionDTO> findById(
            @PathVariable Long id) {

        Consumption consumption = consumptionService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consumption not found"));

        ConsumptionDTO dto =
                modelMapper.map(consumption, ConsumptionDTO.class);

        dto.setIdItem(consumption.getItem().getIdItem());
        dto.setIdRecipe(consumption.getRecipe().getIdRecipe());

        return ResponseEntity.ok(dto);
    }

    // HU20 Consultar historial de consumo por familia
    @GetMapping("/family/{idFamily}")
    public ResponseEntity<List<ConsumptionHistoryResponseDTO>> findByFamilyId(
            @PathVariable Long idFamily) {

        List<ConsumptionHistoryResponseDTO> history =
                consumptionService.findByFamilyId(idFamily)
                        .stream()
                        .map(consumption -> {

                            ConsumptionHistoryResponseDTO dto =
                                    modelMapper.map(
                                            consumption,
                                            ConsumptionHistoryResponseDTO.class);

                            dto.setIdItem(
                                    consumption.getItem().getIdItem());

                            dto.setIngredientName(
                                    consumption.getItem()
                                            .getIngredient()
                                            .getIngredientName());

                            dto.setIdRecipe(
                                    consumption.getRecipe().getIdRecipe());

                            dto.setRecipeName(
                                    consumption.getRecipe()
                                            .getRecipeName());

                            return dto;
                        })
                        .toList();

        if (history.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No consumption history was found for family: "
                            + idFamily);
        }

        return ResponseEntity.ok(history);
    }
}