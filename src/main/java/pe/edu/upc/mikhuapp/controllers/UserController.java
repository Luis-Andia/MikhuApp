
package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.UserDTOInsert;
import pe.edu.upc.mikhuapp.dtos.UserDTOList;
import pe.edu.upc.mikhuapp.dtos.UserDTORegister;

import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.entities.Users;

import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;

import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
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
    private final PasswordEncoder passwordEncoder;

    public UserController(
            IUserService uS,
            IRoleService rS,
            ICountryService pS,
            IFamilyService fS,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder) {

        this.uS = uS;
        this.rS = rS;
        this.pS = pS;
        this.fS = fS;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // LISTAR USUARIOS
    @GetMapping
    public ResponseEntity<List<UserDTOList>> listar() {

        List<UserDTOList> listaUsuarios = uS.list()
                .stream()
                .map(this::toUserDTOList)
                .toList();

        return ResponseEntity.ok(listaUsuarios);
    }

    // REGISTRAR NUEVO USUARIO INDEPENDIENTE
    @PostMapping
    public ResponseEntity<UserDTOList> insertar(
            @Validated @RequestBody UserDTORegister dto) {

        // Verificar si el nombre de usuario ya existe
        if (uS.listByUsername(dto.getNomUser()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El nombre de usuario ya está registrado"
            );
        }

        // Validar que el país exista
        Country country = pS.listid(dto.getIdCountry())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el país con el id: "
                                        + dto.getIdCountry()
                        )
                );

        // Crear el usuario
        Users nuevoUser = new Users();

        nuevoUser.setFirstName(dto.getFirstName());
        nuevoUser.setNomUser(dto.getNomUser());
        nuevoUser.setLastNameUser(dto.getLastNameUser());
        nuevoUser.setAge(dto.getAge());
        nuevoUser.setEmail(dto.getEmail());

        // Cifrar contraseña con BCrypt
        nuevoUser.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        // Habilitar usuario
        nuevoUser.setEnabled(true);

        // Asignar país
        nuevoUser.setCountry(country);

        // Registro independiente
        nuevoUser.setFamily(null);

        // Crear asignación de ROLE_MEMBER
        Role role = new Role();
        role.setRol("ROLE_MEMBER");
        role.setUser(nuevoUser);

        nuevoUser.getRoles().add(role);

        // Guardar usuario y rol mediante CascadeType.ALL
        uS.insert(nuevoUser);

        // Preparar respuesta sin contraseña
        UserDTOList responseDTO = new UserDTOList();

        responseDTO.setIdUser(nuevoUser.getIdUser());
        responseDTO.setNomUser(nuevoUser.getNomUser());
        responseDTO.setLastNameUser(nuevoUser.getLastNameUser());
        responseDTO.setEmail(nuevoUser.getEmail());
        responseDTO.setIdFamily(null);
        responseDTO.setIdCountry(country.getIdCountry());
        responseDTO.setIdRol(obtenerIdsRoles(nuevoUser));

        // Construir ubicación del recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoUser.getIdUser())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // CONSULTAR USUARIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTOList> buscarid(
            @PathVariable("id") Long id) {

        Users users = uS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        UserDTOList responseDTO = toUserDTOList(users);

        return ResponseEntity.ok(responseDTO);
    }

    // CONSULTAR USUARIO POR NOMBRE DE USUARIO
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTOList> buscarporusername(
            @PathVariable("username") String username) {

        Users users = uS.listByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        UserDTOList responseDTO = toUserDTOList(users);

        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOInsert> actualizar_usuario(
            @PathVariable("id") Long id,
            @Validated @RequestBody UserDTOInsert dto) {

        Users users = uS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        // Actualizar contraseña si se proporciona una nueva
        if (dto.getPassword() != null
                && !dto.getPassword().isBlank()) {

            users.setPassword(
                    passwordEncoder.encode(dto.getPassword())
            );
        }

        users.setNomUser(dto.getNomUser());
        users.setLastNameUser(dto.getLastNameUser());
        users.setAge(dto.getAge());
        users.setEmail(dto.getEmail());

        // Validar familia solo cuando se proporciona un ID
        Family family = null;

        if (dto.getIdFamily() != null) {
            family = fS.listid(dto.getIdFamily())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe la familia"
                            )
                    );
        }

        // Validar país
        Country country = pS.listid(dto.getIdCountry())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el país"
                        )
                );

        // Actualizar roles
        List<Role> roles = obtenerRoles(dto.getIdRol());

        users.getRoles().clear();

        for (Role role : roles) {
            role.setUser(users);
            users.getRoles().add(role);
        }

        users.setFamily(family);
        users.setCountry(country);

        Users usersActualizado = uS.update(users);

        UserDTOInsert responseDTO =
                modelMapper.map(usersActualizado, UserDTOInsert.class);

        responseDTO.setIdRol(
                obtenerIdsRoles(usersActualizado)
        );

        return ResponseEntity.ok(responseDTO);
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id) {

        uS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        uS.delete(id);

        return ResponseEntity.noContent().build();
    }

    // CONVERTIR USUARIO A DTO
    private UserDTOList toUserDTOList(Users user) {

        UserDTOList dto = modelMapper.map(
                user,
                UserDTOList.class
        );

        dto.setIdRol(obtenerIdsRoles(user));

        return dto;
    }

    // OBTENER IDS DE LOS ROLES
    private List<Long> obtenerIdsRoles(Users user) {

        return user.getRoles()
                .stream()
                .map(Role::getIdRol)
                .toList();
    }

    // BUSCAR ROLES POR SUS IDS
    private List<Role> obtenerRoles(List<Long> idRoles) {

        List<Role> roles = new ArrayList<>();

        for (Long idRol : idRoles) {

            Role role = rS.listid(idRol)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el rol con el id: " + idRol
                            )
                    );

            roles.add(role);
        }

        return roles;
    }
}
