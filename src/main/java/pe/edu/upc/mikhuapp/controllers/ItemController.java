package pe.edu.upc.mikhuapp.controllers;

import org.springframework.cglib.core.Local;
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
import pe.edu.upc.mikhuapp.repositories.IItemRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredienteService;
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
    private IFamiliaService familiaService;

    @Autowired
    private IIngredienteService ingredienteService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IItemRepository iItemRepository;

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

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ItemDTOList>> buscarPorNombre(@PathVariable String nombre) {

        List<ItemDTOList> lista = itemService.buscarPorNombre(nombre)
                .stream()
                .map(item -> {
                    ItemDTOList dto = modelMapper.map(item, ItemDTOList.class);
                    dto.setIdFamilia(item.getFamilia().getIdFamilia());
                    dto.setIdIngrediente(item.getIngrediente().getIdIngrediente());
                    return dto;
                })
                .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron items con el nombre: " + nombre);
        }

        return ResponseEntity.ok(lista);
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