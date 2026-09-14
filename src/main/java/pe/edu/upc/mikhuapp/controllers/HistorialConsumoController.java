package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.HistorialConsumoDTO;
import pe.edu.upc.mikhuapp.entities.HistorialConsumo;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Receta;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IHistorialConsumoService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/historial-consumo")
public class HistorialConsumoController {

    @Autowired
    private IHistorialConsumoService historialConsumoService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IRecetaService recetaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<HistorialConsumoDTO>> list() {

        List<HistorialConsumoDTO> lista = historialConsumoService.list().stream()
                .map(historial -> {
                    HistorialConsumoDTO dto =
                            modelMapper.map(historial, HistorialConsumoDTO.class);

                    dto.setIdItem(historial.getItem().getIdItem());
                    dto.setIdReceta(historial.getReceta().getIdReceta());

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

        Receta receta = recetaService.listid(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receta no encontrada"));

        HistorialConsumo historial =
                modelMapper.map(dto, HistorialConsumo.class);

        historial.setItem(item);
        historial.setReceta(receta);

        HistorialConsumo historialRegistrado =
                historialConsumoService.insert(historial);

        HistorialConsumoDTO response =
                modelMapper.map(historialRegistrado, HistorialConsumoDTO.class);

        response.setIdItem(item.getIdItem());
        response.setIdReceta(receta.getIdReceta());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(historialRegistrado.getIdConsumo())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialConsumoDTO> listId(
            @PathVariable Long id) {

        HistorialConsumo historial = historialConsumoService.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consumo no encontrado"));

        HistorialConsumoDTO dto =
                modelMapper.map(historial, HistorialConsumoDTO.class);

        dto.setIdItem(historial.getItem().getIdItem());
        dto.setIdReceta(historial.getReceta().getIdReceta());

        return ResponseEntity.ok(dto);
    }
}