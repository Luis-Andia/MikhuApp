package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ItemDTOInsert;
import pe.edu.upc.mikhuapp.dtos.ItemDTOList;
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
    private final IFamilyService familyService;
    private final IIngredientService ingredientService;
    private final ModelMapper modelMapper;

    public ItemController(
            IItemService itemService,
            IFamilyService familyService,
            IIngredientService ingredientService,
            ModelMapper modelMapper) {

        this.itemService = itemService;
        this.familyService = familyService;
        this.ingredientService = ingredientService;
        this.modelMapper = modelMapper;
    }

    // LISTAR ITEM
    @GetMapping
    public ResponseEntity<List<ItemDTOList>> list() {

        List<ItemDTOList> itemList = itemService.list().stream()
                .map(item -> {
                    ItemDTOList dto =
                            modelMapper.map(item, ItemDTOList.class);

                    dto.setIdFamily(item.getFamily().getIdFamily());
                    dto.setIdIngredient(
                            item.getIngredient().getIdIngredient());
                    dto.setIngredientName(
                            item.getIngredient().getIngredientName());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(itemList);
    }

    // INSERTAR ITEM
    @PostMapping
    public ResponseEntity<ItemDTOList> insert(
            @Validated @RequestBody ItemDTOInsert dto) {

        Family family = familyService.findById(dto.getIdFamily())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Family not found"));

        Ingredient ingredient =
                ingredientService.findById(dto.getIdIngredient())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Ingredient not found"));

        Item item = modelMapper.map(dto, Item.class);

        item.setFamily(family);
        item.setIngredient(ingredient);

        Item registeredItem = itemService.insert(item);

        ItemDTOList response =
                modelMapper.map(registeredItem, ItemDTOList.class);

        response.setIdFamily(family.getIdFamily());
        response.setIdIngredient(
                ingredient.getIdIngredient());
        response.setIngredientName(
                ingredient.getIngredientName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredItem.getIdItem())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

    // CONSULTAR ITEM POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTOList> findById(
            @PathVariable Long id) {

        Item item = itemService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found"));

        ItemDTOList dto =
                modelMapper.map(item, ItemDTOList.class);

        dto.setIdFamily(item.getFamily().getIdFamily());
        dto.setIdIngredient(
                item.getIngredient().getIdIngredient());
        dto.setIngredientName(
                item.getIngredient().getIngredientName());

        return ResponseEntity.ok(dto);
    }

    // HU16 BUSCAR ITEM POR NOMBRE DE INGREDIENTE
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ItemDTOList>> searchByName(
            @PathVariable String name) {

        List<ItemDTOList> itemList =
                itemService.searchByName(name)
                        .stream()
                        .map(item -> {
                            ItemDTOList dto =
                                    modelMapper.map(
                                            item,
                                            ItemDTOList.class);

                            dto.setIdFamily(
                                    item.getFamily().getIdFamily());

                            dto.setIdIngredient(
                                    item.getIngredient()
                                            .getIdIngredient());

                            dto.setIngredientName(
                                    item.getIngredient()
                                            .getIngredientName());

                            return dto;
                        })
                        .toList();

        if (itemList.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No items were found with the ingredient name: "
                            + name);
        }

        return ResponseEntity.ok(itemList);
    }

    // LISTAR ITEMS VENCIDOS
    @GetMapping("/expired")
    public ResponseEntity<List<ItemDTOList>> findExpiredItems() {

        LocalDate currentDate = LocalDate.now();

        List<ItemDTOList> itemList =
                itemService.findExpiredItems(currentDate)
                        .stream()
                        .map(item ->
                                modelMapper.map(
                                        item,
                                        ItemDTOList.class))
                        .toList();

        return ResponseEntity.ok(itemList);
    }

    // LISTAR ITEMS PROXIMOS A VENCER
    @GetMapping("/expiring-soon")
    public ResponseEntity<List<ItemDTOList>> findItemsExpiringSoon() {

        LocalDate currentDate = LocalDate.now();
        LocalDate limitDate = currentDate.plusDays(3);

        List<ItemDTOList> itemList =
                itemService.findItemsExpiringSoon(
                                currentDate,
                                limitDate)
                        .stream()
                        .map(item ->
                                modelMapper.map(
                                        item,
                                        ItemDTOList.class))
                        .toList();

        return ResponseEntity.ok(itemList);
    }

    // LISTAR ALIMENTOS BAJO STOCK
    @GetMapping("/low-stock")
    public ResponseEntity<List<ItemDTOList>> findLowStockItems() {

        List<ItemDTOList> itemList =
                itemService.findLowStockItems()
                        .stream()
                        .map(item ->
                                modelMapper.map(
                                        item,
                                        ItemDTOList.class))
                        .toList();

        return ResponseEntity.ok(itemList);
    }

    // ELIMINAR ITEM
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id) {

        Item item = itemService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Item not found"));

        itemService.delete(item.getIdItem());

        return ResponseEntity.noContent().build();
    }
}