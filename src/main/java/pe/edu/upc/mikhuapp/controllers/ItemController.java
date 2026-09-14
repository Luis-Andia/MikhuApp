package pe.edu.upc.mikhuapp.controllers;

import org.springframework.validation.annotation.Validated;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ItemDTOInsert;
import pe.edu.upc.mikhuapp.dtos.ItemDTOList;
import pe.edu.upc.mikhuapp.entities.Familia;
import pe.edu.upc.mikhuapp.entities.Ingrediente;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredienteService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IFamiliaService familiaService;

    @Autowired
    private IIngredienteService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ItemDTOList>> list() {
        List<ItemDTOList> lista = itemService.list().stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
                    dto.setIdFamilia(item.getFamilia().getIdFamilia());
                    dto.setIdIngrediente(item.getIngrediente().getIdIngrediente());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ItemDTOList> insert(@Validated @RequestBody ItemDTOInsert dto) {

        Familia familia = familiaService.listid(dto.getIdFamilia())
                .orElseThrow(() -> new ResourceNotFoundException("Familia no encontrada"));

        Ingrediente ingrediente = ingredienteService.listid(dto.getIdIngrediente())
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        Item item = modelMapper.map(dto, Item.class);

        item.setFamilia(familia);
        item.setIngrediente(ingrediente);

        Item itemRegistrado = itemService.insert(item);

        ItemDTOList response = modelMapper.map(itemRegistrado, ItemDTOList.class);
        response.setIdFamilia(familia.getIdFamilia());
        response.setIdIngrediente(ingrediente.getIdIngrediente());

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
        dto.setIdFamilia(item.getFamilia().getIdFamilia());
        dto.setIdIngrediente(item.getIngrediente().getIdIngrediente());

        return ResponseEntity.ok(dto);
    }
}