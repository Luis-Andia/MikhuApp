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

import javax.swing.text.StyledEditorKit;
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
        Familia nueva_familia = modelMapper.map(familia, Familia.class);
        fS.insert(nueva_familia);

        FamiliaDTOInsert responseDTO = modelMapper.map(nueva_familia, FamiliaDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nueva_familia.getIdFamilia())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    // CONSULTAR familia por ID
    @GetMapping("/{id}")
    public ResponseEntity<FamiliaDTOList> buscarid(@PathVariable Long id){
        Familia familia = fS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        FamiliaDTOList responseDTO = modelMapper.map(familia, FamiliaDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR FAMILIA
    @PutMapping
    public ResponseEntity<FamiliaDTOInsert> actualizarfamilia(@Validated @RequestBody FamiliaDTOInsert dto){
        Optional<Familia> existente = fS.listid(dto.getIdFamilia());
        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("No existe la familia");
        }
        Familia familia = existente.get();

        familia.setNomFamilia(dto.getNomFamilia());
        familia.setContrasenaFamilia(dto.getContrasenaFamilia());

        fS.update(familia);
        FamiliaDTOInsert responseDTO = modelMapper.map(familia, FamiliaDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }
}
