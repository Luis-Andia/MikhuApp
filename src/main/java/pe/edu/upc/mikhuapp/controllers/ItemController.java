package pe.edu.upc.mikhuapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ItemDTOInsert;
import pe.edu.upc.mikhuapp.dtos.ItemDTOList;
import pe.edu.upc.mikhuapp.dtos.FamilyInventoryDTO;
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

    // HU16: INSERTAR ITEM
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
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

    // HU17: LISTAR ITEM
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
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

    // HU18: ACTUALIZAR ITEM
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<ItemDTOList> update(@PathVariable Long id, @Validated @RequestBody ItemDTOInsert dto) {

        Item item = itemService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"));

        Family family = familiaService.listid(dto.getIdFamily())
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        Ingredient ingredient = ingredienteService.listId(dto.getIdIngredient())
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        modelMapper.map(dto, item);

        item.setFamily(family);
        item.setIngredient(ingredient);
        item.setIdItem(dto.getIdItem());

        itemService.update(item);

        ItemDTOList response = modelMapper.map(item, ItemDTOList.class);

        response.setIdFamily(family.getIdFamily());
        response.setIdIngredient(ingredient.getIdIngredient());

        return ResponseEntity.ok(response);
    }

    // HU19: ELIMINAR ITEM
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<Void> eliminar_item(@PathVariable("id") Long id) {
        Item item = itemService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"));

        itemService.delete(item.getIdItem());

        return ResponseEntity.noContent().build();
    }

    // HU20: CONSULTAR UN ITEM POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<ItemDTOList> listId(@PathVariable Long id) {

        Item item = itemService.listid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item no encontrado"));

        ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
        dto.setIdFamily(item.getFamily().getIdFamily());
        dto.setIdIngredient(item.getIngredient().getIdIngredient());

        return ResponseEntity.ok(dto);
    }

    // LISTAR ITEMS VENCIDOS
    @GetMapping("/Vencidos/{idFamily}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity <List<ItemDTOList>>listarVencidos(@RequestParam Long idFamily) {
        Family family = familiaService.listid(idFamily)
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));
        LocalDate fechaActual = LocalDate.now();

        List<ItemDTOList> lista = itemService.listarVencidos(fechaActual, idFamily)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU47: LISTAR ITEMS DEL INVENTARIO FAMILIAR
    @GetMapping("/familia/{idFamily}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<List<FamilyInventoryDTO>> listarItemsPorFamilia(
            @PathVariable("idFamily") Long idFamily) {

        familiaService.listid(idFamily)
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        List<FamilyInventoryDTO> lista = itemService.listarItemsPorFamilia(idFamily)
                .stream()
                .map(item -> {
                    FamilyInventoryDTO dto = new FamilyInventoryDTO();

                    dto.setIdItem(((Number) item[0]).longValue());
                    dto.setIngredientName((String) item[1]);
                    dto.setAmountAvailable(((Number) item[2]).intValue());
                    dto.setPurchaseDate((LocalDate) item[3]);
                    dto.setDueDate((LocalDate) item[4]);
                    dto.setMinimumStock(((Number) item[5]).intValue());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU48: Buscar item por nombre
    @GetMapping("/buscarPorNombre")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {

        List<ItemDTOList> lista = itemService.buscarPorNombre(nombre)
                .stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
                    dto.setIdFamily(item.getFamily().getIdFamily());
                    dto.setIdIngredient(item.getIngredient().getIdIngredient());
                    return dto;
                })
                .toList();

        if (lista.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No hay items con el nombre: " + nombre);
        }

        return ResponseEntity.ok(lista);
    }


    // HU49: LISTAR ALIMENTOS CON BAJO STOCK
    @GetMapping("/bajoStock/{idFamily}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
    public ResponseEntity <List<ItemDTOList>> listarAlimentosBajoStock(@RequestParam Long idFamily) {

        Family family = familiaService.listid(idFamily)
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        List<ItemDTOList> lista = itemService.listarAlimentoBajoStock(idFamily)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }



    // HU50: ALIMENTOS PROXIMOS A VENCER
    @GetMapping("/ProximosVencer")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<List<ItemDTOList>> listarProximosVencer(@RequestParam Long idFamily) {

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(3);

        List<ItemDTOList> lista = itemService
                .listarProximosVencer(idFamily, fechaActual, fechaLimite)
                .stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);

                    dto.setIdFamily(item.getFamily().getIdFamily());
                    dto.setIdIngredient(item.getIngredient().getIdIngredient());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

}