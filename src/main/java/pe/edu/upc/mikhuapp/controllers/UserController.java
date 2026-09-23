package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;
    private final IRoleService roleService;
    private final ICountryService countryService;
    private final IFamilyService familyService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            IUserService userService,
            IRoleService roleService,
            ICountryService countryService,
            IFamilyService familyService,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.roleService = roleService;
        this.countryService = countryService;
        this.familyService = familyService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<UserDTOList>> list() {

        List<UserDTOList> userList = userService.list()
                .stream()
                .map(user -> {

                    UserDTOList dto =
                            modelMapper.map(user, UserDTOList.class);

                    if (user.getRoles() != null
                            && !user.getRoles().isEmpty()) {

                        dto.setIdRole(
                                user.getRoles()
                                        .get(0)
                                        .getIdRole()
                        );
                    }

                    if (user.getFamily() != null) {
                        dto.setIdFamily(
                                user.getFamily().getIdFamily()
                        );
                    }

                    if (user.getCountry() != null) {
                        dto.setIdCountry(
                                user.getCountry().getIdCountry()
                        );
                    }

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(userList);
    }

    // Insertar nuevo usuario
    @PostMapping
    public ResponseEntity<UserDTOInsert> insert(
            @Validated @RequestBody UserDTOInsert userDTO) {

        Role role = roleService.findById(userDTO.getIdRole())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with id: "
                                        + userDTO.getIdRole()));

        Family family = familyService.findById(userDTO.getIdFamily())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Family not found with id: "
                                        + userDTO.getIdFamily()));

        Country country = countryService.findById(userDTO.getIdCountry())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Country not found with id: "
                                        + userDTO.getIdCountry()));

        Users newUser =
                modelMapper.map(userDTO, Users.class);

        // Cifrar contraseña con BCrypt
        newUser.setPassword(
                passwordEncoder.encode(userDTO.getPassword())
        );

        newUser.setRoles(List.of(role));
        newUser.setFamily(family);
        newUser.setCountry(country);

        userService.insert(newUser);

        UserDTOInsert responseDTO =
                modelMapper.map(newUser, UserDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getIdUser())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // Consultar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTOList> findById(
            @PathVariable("id") Long id) {

        Users user = userService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        UserDTOList responseDTO =
                modelMapper.map(user, UserDTOList.class);

        if (user.getRoles() != null
                && !user.getRoles().isEmpty()) {

            responseDTO.setIdRole(
                    user.getRoles()
                            .get(0)
                            .getIdRole()
            );
        }

        if (user.getFamily() != null) {
            responseDTO.setIdFamily(
                    user.getFamily().getIdFamily()
            );
        }

        if (user.getCountry() != null) {
            responseDTO.setIdCountry(
                    user.getCountry().getIdCountry()
            );
        }

        return ResponseEntity.ok(responseDTO);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOInsert> update(
            @PathVariable("id") Long id,
            @Validated @RequestBody UserDTOInsert dto) {

        Users user = userService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Family family = familyService.findById(dto.getIdFamily())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Family not found"));

        Country country = countryService.findById(dto.getIdCountry())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Country not found"));

        Users updatedUser =
                modelMapper.map(dto, Users.class);

        updatedUser.setIdUser(id);
        updatedUser.setFamily(family);
        updatedUser.setCountry(country);

        // Cifrar contraseña con BCrypt
        updatedUser.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        updatedUser.setRoles(user.getRoles());

        userService.update(updatedUser);

        UserDTOInsert responseDTO =
                modelMapper.map(updatedUser, UserDTOInsert.class);

        return ResponseEntity.ok(responseDTO);
    }

    // Listar miembros de una familia
    @GetMapping("/family-members/{familyId}")
    public ResponseEntity<List<UserDTOList>> listFamilyMembers(
            @PathVariable long familyId) {

        List<UserDTOList> userList = userService
                .listFamilyMembers(familyId)
                .stream()
                .map(user -> {

                    UserDTOList dto =
                            modelMapper.map(user, UserDTOList.class);

                    if (user.getRoles() != null
                            && !user.getRoles().isEmpty()) {

                        dto.setIdRole(
                                user.getRoles()
                                        .get(0)
                                        .getIdRole()
                        );
                    }

                    if (user.getFamily() != null) {
                        dto.setIdFamily(
                                user.getFamily().getIdFamily()
                        );
                    }

                    if (user.getCountry() != null) {
                        dto.setIdCountry(
                                user.getCountry().getIdCountry()
                        );
                    }

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(userList);
    }
}