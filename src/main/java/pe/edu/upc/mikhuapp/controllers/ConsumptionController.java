package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.*;
import pe.edu.upc.mikhuapp.entities.Consumption;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IConsumptionService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/consumptions")
public class ConsumptionController {

    private final IConsumptionService cS;
    private final IItemService iS;
    private final IRecipeService rS;
    private final IFamilyService fS;
    private final ModelMapper modelMapper;

    public ConsumptionController(
            IConsumptionService cS,
            IItemService iS,
            IRecipeService rS, IFamilyService fS,
            ModelMapper modelMapper) {

        this.cS = cS;
        this.iS = iS;
        this.rS = rS;
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // HU: Listar items consumidos
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<List<ConsumptionDTO>> list() {

        List<ConsumptionDTO> lista = cS.list()
                .stream()
                .map(historial -> {

                    ConsumptionDTO dto =
                            modelMapper.map(historial, ConsumptionDTO.class);

                    dto.setIdItem(
                            historial.getItem().getIdItem()
                    );

                    dto.setIdReceta(
                            historial.getRecipe().getIdRecipe()
                    );

                    return dto;

                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/consumo/{idFamily}")
    @PreAuthorize("hasAnyRole('MODERATOR','MEMBER', 'GUEST')")
    public ResponseEntity<List<ConsumptionDTO>> listarConsumoPorFamilia(
            @PathVariable("idFamily") Long idFamily) {

        fS.listid(idFamily)
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        List<ConsumptionDTO> lista = cS.findConsumptionbyFamilyid(idFamily)
                .stream()
                .map(row -> {
                    ConsumptionDTO dto = new ConsumptionDTO();

                    dto.setIdConsumption(((Number) row[0]).longValue());
                    dto.setConsumptionDate(toLocalDate(row[1]));
                    dto.setIdItem(((Number) row[2]).longValue());
                    dto.setIdReceta(((Number) row[3]).longValue());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU36: INSERTAR un CONSUMO
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<ConsumptionDTO> insert(@Validated @RequestBody ConsumptionDTO dto) {

        Item item = iS.listid(dto.getIdItem())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item no encontrado"
                        ));

        Recipe recipe = rS.listId(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Receta no encontrada"
                        ));

        Consumption historial =
                modelMapper.map(dto, Consumption.class);

        historial.setItem(item);
        historial.setRecipe(recipe);

        Consumption historialRegistrado =
                cS.insert(historial);

        ConsumptionDTO response =
                modelMapper.map(
                        historialRegistrado,
                        ConsumptionDTO.class
                );

        response.setIdItem(item.getIdItem());
        response.setIdReceta(recipe.getIdRecipe());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        historialRegistrado.getIdConsumption()
                )
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

    // HU38: ACTUALIZAR un CONSUMO
    @PutMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ConsumptionDTO> update(
            @Validated @RequestBody ConsumptionDTO dto) {

        Consumption consumption = cS.listid(
                        dto.getIdConsumption()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Consumo no encontrado"
                        ));

        Item item = iS.listid(dto.getIdItem())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item no encontrado"
                        ));

        Recipe recipe = rS.listId(dto.getIdReceta())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Receta no encontrada"
                        ));

        modelMapper.map(dto, consumption);

        consumption.setItem(item);
        consumption.setRecipe(recipe);
        consumption.setIdConsumption(
                dto.getIdConsumption()
        );

        cS.update(consumption);

        ConsumptionDTO response =
                modelMapper.map(
                        consumption,
                        ConsumptionDTO.class
                );

        return ResponseEntity.ok(response);
    }

    // HU39: ELIMINAR consumo
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ConsumptionDTO> delete(
            @PathVariable("id") Long id) {

        cS.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Consumo no encontrado"
                        ));

        cS.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    // HU40: CONSULTAR un CONSUMO por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<ConsumptionDTO> listId(
            @PathVariable Long id) {

        Consumption historial = cS.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Consumo no encontrado"
                        ));

        ConsumptionDTO dto =
                modelMapper.map(
                        historial,
                        ConsumptionDTO.class
                );

        dto.setIdItem(
                historial.getItem().getIdItem()
        );

        dto.setIdReceta(
                historial.getRecipe().getIdRecipe()
        );

        return ResponseEntity.ok(dto);
    }

    // HU51: Listar alimentos mas consumidos
    @GetMapping("/alimentos-mas-consumidos/{idFamily}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<List<MostConsumedIngredientsDTO>> ListMostConsumedIngredients(@PathVariable Long idFamily) {
        List<MostConsumedIngredientsDTO> list =
                cS.ListMostConsumedIngredients(idFamily)
                        .stream()
                        .map(item -> {
                            MostConsumedIngredientsDTO dto =
                                    new MostConsumedIngredientsDTO();
                            dto.setIdItem(((Number) item[0]).intValue());
                            dto.setNomIngredient((String) item[1]);
                            dto.setQuantity(((Number) item[2]).intValue());
                            return dto;
                        })
                        .toList();
        return ResponseEntity.ok(list);
    }

    // Alimentos mas consumidos GLOBAL
    @GetMapping("/alimentos-mas-consumidos-global")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<List<MostConsumedIngredientsDTO>> ListMostConsumedIngredientsGlobal() {
        List<MostConsumedIngredientsDTO> list =
                cS.ListMostConsumedIngredientsGlobal()
                        .stream()
                        .map(item -> {
                            MostConsumedIngredientsDTO dto =
                                    new MostConsumedIngredientsDTO();
                            dto.setIdItem(((Number) item[0]).intValue());
                            dto.setNomIngredient((String) item[1]);
                            dto.setQuantity(((Number) item[2]).intValue());
                            return dto;
                        })
                        .toList();
        return ResponseEntity.ok(list);
    }

    // HU55: CONSULTAR HISTORIAL DE CONSUMO POR IdFamily
    @GetMapping("/familia/{idFamily}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<List<HistorialConsumoResponseDTO>> consultarPorFamilia(@PathVariable Long idFamily) {

        List<Consumption> consumptions =
                cS.consultarPorFamilia(idFamily);

        List<HistorialConsumoResponseDTO> lista =
                consumptions
                        .stream()
                        .map(consumption -> {

                            HistorialConsumoResponseDTO dto =
                                    new HistorialConsumoResponseDTO();

                            dto.setIdConsumption(
                                    consumption.getIdConsumption()
                            );

                            dto.setIdItem(
                                    consumption.getItem().getIdItem()
                            );

                            dto.setNomIngredient(
                                    consumption.getItem()
                                            .getIngredient()
                                            .getNomIngredient()
                            );

                            dto.setIdRecipe(
                                    consumption.getRecipe()
                                            .getIdRecipe()
                            );

                            dto.setNomRecipe(
                                    consumption.getRecipe()
                                            .getNomRecipe()
                            );

                            dto.setConsumptionDate(
                                    consumption.getConsumptionDate()
                            );

                            return dto;
                        })
                        .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No hay consumos registrados para la familia: "
                            + idFamily
            );
        }

        return ResponseEntity.ok(lista);
    }

    // HU56: CONSULTAR ITEMS CONSUMIDOS POR FECHA
    @GetMapping("/fecha")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MostConsumedIngredientsDTO>> consultaritemsconsumidosPorFecha(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException(
                    "La fecha inicial no puede ser posterior a la fecha final"
            );
        }

        List<MostConsumedIngredientsDTO> list =
                cS.IngredientesConsumidosPorFecha(fechaInicio, fechaFin)
                        .stream()
                        .map(item -> {
                            MostConsumedIngredientsDTO dto = new MostConsumedIngredientsDTO();
                            dto.setIdItem(((Number) item[0]).intValue());
                            dto.setNomIngredient((String) item[1]);
                            dto.setQuantity(((Number) item[2]).intValue());
                            return dto;
                        })
                        .toList();

        return ResponseEntity.ok(list);
    }

    private LocalDate toLocalDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate localDate) {
            return localDate;
        }
        if (value instanceof java.sql.Date date) {
            return date.toLocalDate();
        }
        if (value instanceof java.sql.Timestamp timestamp) {
            return timestamp.toLocalDateTime().toLocalDate();
        }
        throw new IllegalArgumentException("Formato de fecha de consumo no válido: " + value.getClass().getName());
    }
}