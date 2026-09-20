package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.FamiliaDTOInsert;
import pe.edu.upc.mikhuapp.dtos.FamiliaDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Family nueva_family = modelMapper.map(familia, Family.class);
        fS.insert(nueva_family);

        FamiliaDTOInsert responseDTO = modelMapper.map(nueva_family, FamiliaDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nueva_family.getIdFamily())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    // CONSULTAR familia por ID
    @GetMapping("/{id}")
    public ResponseEntity<FamiliaDTOList> buscarid(@PathVariable Long id){
        Family family = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        FamiliaDTOList responseDTO = modelMapper.map(family, FamiliaDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR FAMILIA
    @PutMapping
    public ResponseEntity<FamiliaDTOInsert> actualizarfamilia(@Validated @RequestBody FamiliaDTOInsert dto){
        Optional<Family> existente = fS.listid(dto.getIdFamilia());
        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("No existe la familia");
        }
        Family family = existente.get();

        family.setNomFamily(dto.getNomFamilia());
        family.setPasswordFamily(dto.getContrasenaFamilia());

        fS.update(family);
        FamiliaDTOInsert responseDTO = modelMapper.map(family, FamiliaDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }
}
