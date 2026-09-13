package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.FamiliaDTOInsert;
import pe.edu.upc.mikhuapp.dtos.FamiliaDTOList;
import pe.edu.upc.mikhuapp.entities.Familia;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/familia")
public class FamiliaController {
    // Inyecciones
    private final IFamiliaService fS;
    private final ModelMapper modelMapper;

    public FamiliaController(IFamiliaService fS, ModelMapper modelMapper) {
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // METODOS

    // LISTAR
    @GetMapping
    public ResponseEntity<List<FamiliaDTOList>> listar(){
        List<FamiliaDTOList> lista_familias = fS.list()
                .stream()
                .map(f -> modelMapper.map(f, FamiliaDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_familias);
    }

    // INSERTAR
    @PostMapping
    public ResponseEntity<FamiliaDTOInsert> insertar(@Validated @RequestBody FamiliaDTOInsert familia){
        Familia nueva_familia = modelMapper.map(familia, Familia.class);
        fS.insert(nueva_familia);

        FamiliaDTOInsert responseDTO = modelMapper.map(nueva_familia, FamiliaDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nueva_familia.getId_Familia())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    // CONSULTAR familia por ID
    @GetMapping("/{id}")
    public ResponseEntity<FamiliaDTOList> buscar_id(@PathVariable Long id){
        Familia familia = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        FamiliaDTOList responseDTO = modelMapper.map(familia, FamiliaDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }


}
