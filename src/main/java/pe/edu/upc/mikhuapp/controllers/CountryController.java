package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.CountryDTO;
import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    // Inyecciones
    private final ICountryService pS;
    private final ModelMapper modelMapper;

    public CountryController(ICountryService pS, ModelMapper modelMapper) {
        this.pS = pS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // HU31: REGISTRAR NUEVO PAIS
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryDTO> registrar(@Validated @RequestBody CountryDTO dto){
        Country nuevo_country = modelMapper.map(dto, Country.class);
        pS.insert(nuevo_country);

        CountryDTO responseDTO = modelMapper.map(nuevo_country, CountryDTO.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_country.getIdCountry())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // HU23: LISTAR PAISES
    @GetMapping // Todos pueden listar los paises
    public ResponseEntity<List<CountryDTO>> listar_paises(){
        List<CountryDTO> lista_paises = pS.list()
                .stream()
                .map(p -> modelMapper.map(p, CountryDTO.class))
                .toList();
        return ResponseEntity.ok(lista_paises);
    }

    // HU33: ACTUALIZAR PAIS
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryDTO> actualizar_pais(@PathVariable("id") long id, @Validated @RequestBody CountryDTO dto){

        // AGREGAR VALIDACION DE ID valido
        Country country = modelMapper.map(dto, Country.class);
        country.setIdCountry(id);
        pS.update(country);
        CountryDTO responseDTO = modelMapper.map(country, CountryDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // HU34: ELIMINAR PAIS
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Country p = pS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        pS.delete(p.getIdCountry());
        return ResponseEntity.noContent().build();
    }

    // HU35: CONSULTAR PAIS POR ID
    @GetMapping("/{id}") // Todos pueden consultar un pais
    public ResponseEntity<CountryDTO> buscar_pais_id(@PathVariable Long id){
        Country p = pS.listid(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));
        CountryDTO responseDTO = modelMapper.map(p, CountryDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

}
