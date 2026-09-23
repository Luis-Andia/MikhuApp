package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/role")
public class RoleController {

    private final IRoleService roleService;
    private final ModelMapper modelMapper;

    public RoleController(
            IRoleService roleService,
            ModelMapper modelMapper) {

        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // Listar
    @GetMapping
    public ResponseEntity<List<RoleDTO>> list() {

        List<RoleDTO> roleList = roleService.list()
                .stream()
                .map(role ->
                        modelMapper.map(role, RoleDTO.class))
                .toList();

        return ResponseEntity.ok(roleList);
    }

    // Insertar
    @PostMapping
    public ResponseEntity<RoleDTO> insert(
            @Validated @RequestBody RoleDTO dto) {

        Role newRole =
                modelMapper.map(dto, Role.class);

        roleService.insert(newRole);

        RoleDTO responseDTO =
                modelMapper.map(newRole, RoleDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRole.getIdRole())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(
            @PathVariable Long id) {

        Role role = roleService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        RoleDTO responseDTO =
                modelMapper.map(role, RoleDTO.class);

        return ResponseEntity.ok(responseDTO);
    }
}