package pe.edu.upc.mikhuapp.controllers;

import org.springframework.validation.annotation.Validated;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ItemDTOInsert;
import pe.edu.upc.mikhuapp.dtos.ItemDTOList;
import pe.edu.upc.mikhuapp.dtos.ItemResponseDTO;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final IItemService itemService;
    private final IFamilyService familiaService;
    private final IIngredientService ingredienteService;
    private final ModelMapper modelMapper;

    public ItemController(IItemService itemService, IFamilyService familiaService, IIngredientService ingredienteService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.familiaService = familiaService;
        this.ingredienteService = ingredienteService;
        this.modelMapper = modelMapper;
    }

    // LISTAR ITEM
    @GetMapping
    public ResponseEntity<List<ItemDTOList>> list() {
        List<ItemDTOList> lista = itemService.list().stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
                    dto.setIdFamily(item.getFamily().getIdFamily());
                    dto.setIdIngredient(item.getIngredient().getIdIngredient());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    // INSERTAR ITEM
    @PostMapping
    public ResponseEntity<ItemDTOList> insert(@Validated @RequestBody ItemDTOInsert dto) {

        Family family = familiaService.listid(dto.getIdFamily())
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        Ingredient ingredient = ingredienteService.listId(dto.getIdIngredient())
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        Item item = modelMapper.map(dto, Item.class);

        item.setFamily(family);
        item.setIngredient(ingredient);

        Item itemRegistrado = itemService.insert(item);

        ItemDTOList response = modelMapper.map(itemRegistrado, ItemDTOList.class);
        response.setIdFamily(family.getIdFamily());
        response.setIdIngredient(ingredient.getIdIngredient());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemRegistrado.getIdItem())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTOList> listId(@PathVariable Long id) {

        Item item = itemService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"));

        ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
        dto.setIdFamily(item.getFamily().getIdFamily());
        dto.setIdIngredient(item.getIngredient().getIdIngredient());

        return ResponseEntity.ok(dto);
    }

    // LISTAR ITEMS VENCIDOS
    @GetMapping("/Vencidos")
    public ResponseEntity <List<ItemDTOList>>listarVencidos() {
        LocalDate fechaActual = LocalDate.now();

        List<ItemDTOList> lista = itemService.listarVencidos(fechaActual)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ProximosVencer")
    public ResponseEntity <List<ItemDTOList>> listarProximosVencer() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(3);

        List<ItemDTOList> lista = itemService.listarProximosVencer(fechaActual, fechaLimite)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/bajoStock")
    public ResponseEntity <List<ItemDTOList>> listarAlimentosBajoStock() {
        List<ItemDTOList> lista = itemService.listarAlimentoBajoStock()
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU47 LISTAR ITEMS DEL INVENTARIO FAMILIAR
    @GetMapping("/familia/{idFamily}")
    public ResponseEntity<List<ItemResponseDTO>> listarItemsPorFamilia(
            @PathVariable("idFamily") Long idFamily) {

        familiaService.listid(idFamily)
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        List<ItemResponseDTO> lista = itemService.listarItemsPorFamilia(idFamily)
                .stream()
                .map(item -> {
                    ItemResponseDTO dto = modelMapper.map(item, ItemResponseDTO.class);
                    dto.setNombreIngrediente(item.getIngredient().getNomIngredient());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar_item(@PathVariable("id") Long id) {
        Item item = itemService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"));

        itemService.delete(item.getIdItem());

        return ResponseEntity.noContent().build();
    }


}