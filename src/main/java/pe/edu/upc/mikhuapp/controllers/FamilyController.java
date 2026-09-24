package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/families")
public class FamilyController {
    // Inyecciones
    private final IFamilyService fS;
    private final ModelMapper modelMapper;

    public FamilyController(IFamilyService fS, ModelMapper modelMapper) {
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // METODOS

    // HU26: INSERTAR Familiar
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','MEMBER')")
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

    // HU27: LISTAR Familias
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FamilyDTOList>> listar(){
        List<FamilyDTOList> lista_familias = fS.list()
                .stream()
                .map(f -> modelMapper.map(f, FamilyDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_familias);
    }

    // HU28: ACTUALIZAR FAMILIA
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<FamilyDTOInsert> actualizarfamilia(@Validated @RequestBody FamilyDTOInsert dto){
        Optional<Family> existente = fS.listid(dto.getIdFamily());
        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("No existe la familia");
        }
        Family family = existente.get();

        family.setNomFamily(dto.getNomFamily());
        family.setPasswordFamily(dto.getPasswordFamily());

        fS.update(family);
        FamilyDTOInsert responseDTO = modelMapper.map(family, FamilyDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }

    // HU29: ELIMINAR FAMILIA
    @DeleteMapping("/{id}/familia/{idFamilia}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Void> eliminar_integrante_familia(@PathVariable Long id, @PathVariable Long idFamilia){
        Family familia = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));

        fS.delete(familia.getIdFamily());
        return ResponseEntity.noContent().build();
    }

    // HU30: CONSULTAR familia por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FamilyDTOList> buscarid(@PathVariable Long id){
        Family family = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        FamilyDTOList responseDTO = modelMapper.map(family, FamilyDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }
}
