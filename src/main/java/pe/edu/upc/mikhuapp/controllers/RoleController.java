package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.RoleDTO;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRoleService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RoleController {
    private final IRoleService rS;
    private final ModelMapper modelMapper;

    public RoleController(IRoleService rS, ModelMapper modelMapper) {
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // Listar
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleDTO>> list(){
        List<RoleDTO> lista_roles = rS.list()
                .stream()
                .map(r -> modelMapper.map(r, RoleDTO.class))
                .toList();
        return ResponseEntity.ok(lista_roles);
    }

    // Insertar
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> insertar(@Validated @RequestBody RoleDTO dto){
        Role nuevo_rol = modelMapper.map(dto, Role.class);
        rS.insert(nuevo_rol);
        RoleDTO responseDTO = modelMapper.map(nuevo_rol, RoleDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(nuevo_rol.getIdRol())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> buscar_rol_id(@PathVariable Long id){
        Role rol = rS.listid(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el rol"));
        RoleDTO responseDTO = modelMapper.map(rol, RoleDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // HU08 Actualizar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> actualizarRol(@PathVariable("id") Long id, @Validated @RequestBody RoleDTO dto){
        Role rol = rS.listid(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el rol"));

        Role role_actualizado = modelMapper.map(dto, Role.class);
        role_actualizado.setIdRol(rol.getIdRol());
        rS.update(role_actualizado);

        RoleDTO responseDTO = modelMapper.map(role_actualizado, RoleDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    // HU09 Eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarRol(@PathVariable("id") Long id){
        Role rol = rS.listid(id)
                .orElseThrow(()-> new ResourceNotFoundException("No existe el rol"));

        rS.delete(rol.getIdRol());
        return ResponseEntity.noContent().build();
    }
}
