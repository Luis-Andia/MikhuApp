package pe.edu.upc.mikhuapp.controllers;

import org.springframework.validation.annotation.Validated;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ItemDTOInsert;
import pe.edu.upc.mikhuapp.dtos.ItemDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.repositories.IItemRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IFamilyService familiaService;

    @Autowired
    private IIngredientService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IItemRepository iItemRepository;

    @GetMapping
    public ResponseEntity<List<ItemDTOList>> list() {
        List<ItemDTOList> lista = itemService.list().stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
                    dto.setIdFamily(item.getFamilia().getIdFamily());
                    dto.setIdIngredient(item.getIngrediente().getIdIngrediente());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ItemDTOList> insert(@Validated @RequestBody ItemDTOInsert dto) {

        Family family = familiaService.listid(dto.getIdFamily())
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        Ingredient ingredient = ingredienteService.listid(dto.getIdIngredient())
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        Item item = modelMapper.map(dto, Item.class);

        item.setFamilia(family);
        item.setIngrediente(ingredient);

        Item itemRegistrado = itemService.insert(item);

        ItemDTOList response = modelMapper.map(itemRegistrado, ItemDTOList.class);
        response.setIdFamily(family.getIdFamily());
        response.setIdIngredient(ingredient.getIdIngrediente());

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
        dto.setIdFamily(item.getFamilia().getIdFamily());
        dto.setIdIngredient(item.getIngrediente().getIdIngrediente());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/Vencidos")
    public ResponseEntity <List<ItemDTOList>>listarVencidos() {
        LocalDate fechaActual = LocalDate.now();

        List<ItemDTOList> lista = iItemRepository.findByfechaVencimientoBefore(fechaActual)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ProximosVencer")
    public ResponseEntity <List<ItemDTOList>> listarProximosVencer() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(3);

        List<ItemDTOList> lista = iItemRepository.findByfechaVencimientoBetween(fechaActual, fechaLimite)
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/bajoStock")
    public ResponseEntity <List<ItemDTOList>> listarAlimentosBajoStock() {
        List<ItemDTOList> lista = iItemRepository.findAlimentosBajoStock()
                .stream()
                .map(item->modelMapper.map(item, ItemDTOList.class))
                .toList();

        return ResponseEntity.ok(lista);
    }


}