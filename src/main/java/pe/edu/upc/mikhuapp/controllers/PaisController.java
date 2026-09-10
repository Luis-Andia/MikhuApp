package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.PaisDTO;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pais")
public class PaisController {
    // Inyecciones
    private final IPaisService pS;
    private final ModelMapper modelMapper;

    public PaisController(IPaisService pS, ModelMapper modelMapper) {
        this.pS = pS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // REGISTRAR NUEVO PAIS
    @PostMapping
    public ResponseEntity<PaisDTO> registrar(@Validated @RequestBody PaisDTO dto){
        Pais nuevo_pais = modelMapper.map(dto, Pais.class);
        pS.insert(nuevo_pais);

        PaisDTO responseDTO = modelMapper.map(nuevo_pais, PaisDTO.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_pais.getId_Pais())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // LISTAR PAISES
    @GetMapping
    public ResponseEntity<List<PaisDTO>> listar_paises(){
        List<PaisDTO> lista_paises = pS.list()
                .stream()
                .map(p -> modelMapper.map(p, PaisDTO.class))
                .toList();
        return ResponseEntity.ok(lista_paises);
    }

    // ACTUALIZAR PAIS
    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> actualizar_pais(@PathVariable("id") long id, @Validated @RequestBody PaisDTO dto){

        // AGREGAR VALIDACION DE ID valido
        Pais pais = modelMapper.map(dto, Pais.class);
        pais.setId_Pais(id);
        pS.update(pais);
        PaisDTO responseDTO = modelMapper.map(pais, PaisDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // CONSULTAR PAIS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> buscar_pais_id(@PathVariable Long id){
        Pais p = pS.listID(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        PaisDTO responseDTO = modelMapper.map(p, PaisDTO.class);
        return ResponseEntity.ok(responseDTO);
    }


    // ELIMINAR PAIS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Pais p = pS.listID(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        pS.delete(p.getId_Pais());
        return ResponseEntity.noContent().build();
    }
}
