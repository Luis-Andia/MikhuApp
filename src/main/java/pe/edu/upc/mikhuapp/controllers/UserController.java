package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/usuario")
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
    public ResponseEntity<List<UserDTOList>> listar(){
        List<UserDTOList> lista_usuarios = uS.list()
                .stream()
                .map(u->modelMapper.map(u, UserDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }

    // INSERTAR nuevo USUARIO
    @PostMapping
    public ResponseEntity<UserDTOInsert> insertar(
            @Validated @RequestBody UserDTOInsert usuario){

        Role role = rS.listid(usuario.getIdRol())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el rol con el id: "
                                        + usuario.getIdRol()
                        ));

        Family family = fS.listid(usuario.getIdFamilia())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la familia con el id: "
                                        + usuario.getIdFamilia()
                        ));

        Country country = pS.listid(usuario.getIdPais())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el pais con el id: "
                                        + usuario.getIdPais()
                        ));

        Users nuevo_users =
                modelMapper.map(usuario, Users.class);

        nuevo_users.setRoles(List.of(role)); // VERIFICAR
        nuevo_users.setFamily(family);
        nuevo_users.setCountry(country);

        uS.insert(nuevo_users);

        UserDTOInsert responseDTO =
                modelMapper.map(nuevo_users, UserDTOInsert.class);

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
    public ResponseEntity<UserDTOList> buscarid(@PathVariable("id") Long id){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        UserDTOList responseDTO = modelMapper.map(users, UserDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOInsert> actualizar_usuario(@PathVariable("id")Long id, @Validated @RequestBody UserDTOInsert dto){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        Family family = fS.listid(dto.getIdFamilia())
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        Country country = pS.listid(dto.getIdPais())
                .orElseThrow(()->new ResourceNotFoundException("No existe el pais"));

        Users users_actualizado = modelMapper.map(dto, Users.class);
        users_actualizado.setIdUser(id);
        users_actualizado.setFamily(family);
        users_actualizado.setCountry(country);
        uS.update(users_actualizado);
        UserDTOInsert responseDTO = modelMapper.map(users_actualizado, UserDTOInsert.class);
        return ResponseEntity.ok(responseDTO);

    }
    //LISTAR INTEGRANTES DE UNA FAMILIA POR ID DE FAMILIA
    @GetMapping("/IntegrantesFamilia/{idFamilia}")
    public ResponseEntity<List<UserDTOList>> listmembers(@PathVariable long idFamilia){
        Family familia = fS.listid(idFamilia)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe la familia con el id: " + idFamilia
                    )
                );

        List<UserDTOList> lista_usuarios = uS.listmembers(idFamilia)
                .stream()
                .map(u->modelMapper.map(u, UserDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }
}
