package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RolDTO;
import pe.edu.upc.mikhuapp.entities.Rol;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    private final IRolService rS;
    private final ModelMapper modelMapper;

    public RolController(IRolService rS, ModelMapper modelMapper) {
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // Listar
    @GetMapping
    public ResponseEntity<List<RolDTO>> list(){
        List<RolDTO> lista_roles = rS.list()
                .stream()
                .map(r -> modelMapper.map(r,RolDTO.class))
                .toList();
        return ResponseEntity.ok(lista_roles);
    }

    // Insertar
    @PostMapping
    public ResponseEntity<RolDTO> insertar(@Validated @RequestBody RolDTO dto){
        Rol nuevo_rol = modelMapper.map(dto, Rol.class);
        rS.insert(nuevo_rol);
        RolDTO responseDTO = modelMapper.map(nuevo_rol, RolDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_rol.getId_Rol())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> buscar_rol_id(@PathVariable Long id){
        Rol rol = rS.listid(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el rol"));
        RolDTO responseDTO = modelMapper.map(rol,RolDTO.class);
        return ResponseEntity.ok(responseDTO);
    }
}
