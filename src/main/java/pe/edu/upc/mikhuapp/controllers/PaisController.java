package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.PaisDTOInsert;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;

import java.net.URI;

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
    public ResponseEntity<PaisDTOInsert> registrar(@Validated PaisDTOInsert dto){ // Falta @RequestBody
        Pais nuevo_pais = modelMapper.map(dto, Pais.class);
        pS.insert(nuevo_pais);

        PaisDTOInsert responseDTO = modelMapper.map(nuevo_pais, PaisDTOInsert.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_pais.getId_Pais())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // ACTUALIZAR PAIS

    // ELIMINAR PAIS
}
