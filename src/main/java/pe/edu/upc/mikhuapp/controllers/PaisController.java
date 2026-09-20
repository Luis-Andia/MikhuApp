package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.PaisDTO;
import pe.edu.upc.mikhuapp.entities.Country;
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
        Country nuevo_country = modelMapper.map(dto, Country.class);
        pS.insert(nuevo_country);

        PaisDTO responseDTO = modelMapper.map(nuevo_country, PaisDTO.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_country.getIdCountry())
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
        Country country = modelMapper.map(dto, Country.class);
        country.setIdCountry(id);
        pS.update(country);
        PaisDTO responseDTO = modelMapper.map(country, PaisDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // CONSULTAR PAIS POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> buscar_pais_id(@PathVariable Long id){
        Country p = pS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        PaisDTO responseDTO = modelMapper.map(p, PaisDTO.class);
        return ResponseEntity.ok(responseDTO);
    }


    // ELIMINAR PAIS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Country p = pS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        pS.delete(p.getIdCountry());
        return ResponseEntity.noContent().build();
    }
}
