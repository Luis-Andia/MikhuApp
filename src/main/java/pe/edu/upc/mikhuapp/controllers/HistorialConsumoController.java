package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.HistorialConsumoDTO;
import pe.edu.upc.mikhuapp.dtos.HistorialConsumoResponseDTO;
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
    public ResponseEntity<List<HistorialConsumoResponseDTO>> list() {

        List<HistorialConsumoResponseDTO> lista = historialConsumoService.list().stream()
                .map(historial -> {
                    HistorialConsumoResponseDTO dto =
                            modelMapper.map(historial, HistorialConsumoResponseDTO.class);

                    dto.setIdItem(historial.getItem().getIdItem());
                    dto.setNombreIngrediente(
                            historial.getItem().getIngrediente().getNomIngrediente()
                    );
                    dto.setIdReceta(historial.getReceta().getIdReceta());

                    return dto;
                }).toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<HistorialConsumoResponseDTO> insert(
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

        HistorialConsumoResponseDTO response =
                modelMapper.map(historialRegistrado, HistorialConsumoResponseDTO.class);

        response.setIdItem(item.getIdItem());
        response.setNombreIngrediente(
                item.getIngrediente().getNomIngrediente()
        );
        response.setIdReceta(receta.getIdReceta());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(historialRegistrado.getIdConsumo())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialConsumoResponseDTO> listId(
            @PathVariable Long id) {

        HistorialConsumo historial = historialConsumoService.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consumo no encontrado"));

        HistorialConsumoResponseDTO dto =
                modelMapper.map(historial, HistorialConsumoResponseDTO.class);

        dto.setIdItem(historial.getItem().getIdItem());
        dto.setNombreIngrediente(
                historial.getItem().getIngrediente().getNomIngrediente()
        );
        dto.setIdReceta(historial.getReceta().getIdReceta());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/familia/{idFamilia}")
    public ResponseEntity<List<HistorialConsumoResponseDTO>> listarPorFamilia(
            @PathVariable Long idFamilia) {

        List<HistorialConsumoResponseDTO> lista = historialConsumoService
                .listarPorFamilia(idFamilia)
                .stream()
                .map(historial -> {
                    HistorialConsumoResponseDTO dto =
                            modelMapper.map(historial, HistorialConsumoResponseDTO.class);

                    dto.setIdItem(historial.getItem().getIdItem());
                    dto.setNombreIngrediente(
                            historial.getItem().getIngrediente().getNomIngrediente()
                    );
                    dto.setIdReceta(historial.getReceta().getIdReceta());

                    return dto;
                })
                .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontró historial de consumo para la familia: " + idFamilia);
        }

        return ResponseEntity.ok(lista);
    }
}