package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.FamilyDTOInsert;
import pe.edu.upc.mikhuapp.dtos.FamilyDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/familia")
public class FamilyController {
    // Inyecciones
    private final IFamilyService fS;
    private final ModelMapper modelMapper;

    public FamilyController(IFamilyService fS, ModelMapper modelMapper) {
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // METODOS

    // LISTAR
    @GetMapping
    public ResponseEntity<List<FamilyDTOList>> listar(){
        List<FamilyDTOList> lista_familias = fS.list()
                .stream()
                .map(f -> modelMapper.map(f, FamilyDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_familias);
    }

    // INSERTAR
    @PostMapping
    public ResponseEntity<FamilyDTOInsert> insertar(@Validated @RequestBody FamilyDTOInsert familia){
        Family nueva_family = modelMapper.map(familia, Family.class);
        fS.insert(nueva_family);

        FamilyDTOInsert responseDTO = modelMapper.map(nueva_family, FamilyDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nueva_family.getIdFamily())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    // CONSULTAR familia por ID
    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTOList> buscarid(@PathVariable Long id){
        Family family = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        FamilyDTOList responseDTO = modelMapper.map(family, FamilyDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR FAMILIA
    @PutMapping
    public ResponseEntity<FamilyDTOInsert> actualizarfamilia(@Validated @RequestBody FamilyDTOInsert dto){
        Optional<Family> existente = fS.listid(dto.getIdFamilia());
        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("No existe la familia");
        }
        Family family = existente.get();

        family.setNomFamily(dto.getNomFamilia());
        family.setPasswordFamily(dto.getContrasenaFamilia());

        fS.update(family);
        FamilyDTOInsert responseDTO = modelMapper.map(family, FamilyDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ELIMINAR INTEGRANTE DE FAMILIA
    @DeleteMapping("/{id}/familia/{idFamilia}")
    public ResponseEntity<Void> eliminar_integrante_familia(@PathVariable Long id, @PathVariable Long idFamilia){
        Family familia = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));

        familia.setIdFamily(idFamilia);
        fS.update(familia);

        return ResponseEntity.noContent().build();
    }
}
