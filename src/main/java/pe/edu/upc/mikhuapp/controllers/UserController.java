package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.ConsumptionDTO;
import pe.edu.upc.mikhuapp.dtos.UserDTOInsert;
import pe.edu.upc.mikhuapp.dtos.UserDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.entities.Users;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRoleService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService uS;
    private final IRoleService rS;
    private final ICountryService pS;
    private final IFamilyService fS;
    private final ModelMapper modelMapper;

    public UserController(IUserService uS, IRoleService rS, ICountryService pS, IFamilyService fS, ModelMapper modelMapper) {
        this.uS = uS;
        this.rS = rS;
        this.pS = pS;
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // Listar usuarios
    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTOList>> listar(){
        List<UserDTOList> lista_usuarios = uS.list()
                .stream()
                .map(this::toUserDTOList)
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }

    // INSERTAR nuevo USUARIO
    @PostMapping
    public ResponseEntity<UserDTOInsert> insertar(
            @Validated @RequestBody UserDTOInsert usuario){

        List<Role> roles = obtenerRoles(usuario.getIdRol());

        Family family = fS.listid(usuario.getIdFamily())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la familia con el id: "
                                        + usuario.getIdFamily()
                        ));

        Country country = pS.listid(usuario.getIdCountry())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el pais con el id: "
                                        + usuario.getIdCountry()
                        ));

        Users nuevo_users =
                modelMapper.map(usuario, Users.class);

        roles.forEach(role -> role.setUser(nuevo_users));
        nuevo_users.setRoles(roles);
        nuevo_users.setFamily(family);
        nuevo_users.setCountry(country);

        uS.insert(nuevo_users);

        UserDTOInsert responseDTO =
                modelMapper.map(nuevo_users, UserDTOInsert.class);
        responseDTO.setIdRol(obtenerIdsRoles(nuevo_users));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo_users.getIdUser())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // CONSULTAR USUARIO por ID
    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTOList> buscarid(@PathVariable("id") Long id){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        UserDTOList responseDTO = toUserDTOList(users);
        return ResponseEntity.ok(responseDTO);
    }
    // Consultar usuario por username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTOList> buscarporusername(@PathVariable("username") String username){
        Users users = uS.listByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        UserDTOList responseDTO = toUserDTOList(users);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTOInsert> actualizar_usuario(@PathVariable("id")Long id, @Validated @RequestBody UserDTOInsert dto){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        users.setPassword(dto.getPassword());
        users.setNomUser(dto.getNomUser());
        users.setLastNameUser(dto.getLastNameUser());
        users.setAge(dto.getAge());
        users.setEmail(dto.getEmail());

        Family family = fS.listid(dto.getIdFamily())
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        Country country = pS.listid(dto.getIdCountry())
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));

        List<Role> roles = obtenerRoles(dto.getIdRol());
        roles.forEach(role -> role.setUser(users));
        users.setRoles(roles);
        users.setFamily(family);
        users.setCountry(country);
        Users users_actualizado = uS.update(users);
        UserDTOInsert responseDTO = modelMapper.map(users_actualizado, UserDTOInsert.class);
        responseDTO.setIdRol(obtenerIdsRoles(users_actualizado));
        return ResponseEntity.ok(responseDTO);

    }

    // Eliminar USUARIO
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTOList> delete(@PathVariable("id") Long id) {
        uS.listId(id).
                orElseThrow(() -> new  ResourceNotFoundException("Usuario no encontrado"));

        uS.delete(id);
        return  ResponseEntity.noContent().build();
    }

    private UserDTOList toUserDTOList(Users user) {
        UserDTOList dto = modelMapper.map(user, UserDTOList.class);
        dto.setIdRol(obtenerIdsRoles(user));
        return dto;
    }

    private List<Long> obtenerIdsRoles(Users user) {
        return user.getRoles().stream()
                .map(Role::getIdRol)
                .toList();
    }

    private List<Role> obtenerRoles(List<Long> idRoles) {
        List<Role> roles = new ArrayList<>();
        for (Long idRol : idRoles) {
            Role role = rS.listid(idRol)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No existe el rol con el id: " + idRol
                    ));
            roles.add(role);
        }
        return roles;
    }
}
