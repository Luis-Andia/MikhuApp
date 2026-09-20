package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.UsuarioDTOInsert;
import pe.edu.upc.mikhuapp.dtos.UsuarioDTOList;
import pe.edu.upc.mikhuapp.entities.Familia;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.entities.Users;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUsuarioService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final IUsuarioService uS;
    private final IRolService rS;
    private final IPaisService pS;
    private final IFamiliaService fS;
    private final ModelMapper modelMapper;

    public UsuarioController(IUsuarioService uS, IRolService rS, IPaisService pS, IFamiliaService fS, ModelMapper modelMapper) {
        this.uS = uS;
        this.rS = rS;
        this.pS = pS;
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // Metodos

    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTOList>> listar(){
        List<UsuarioDTOList> lista_usuarios = uS.list()
                .stream()
                .map(u->modelMapper.map(u, UsuarioDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }

    // INSERTAR nuevo USUARIO
    @PostMapping
    public ResponseEntity<UsuarioDTOInsert> insertar(
            @Validated @RequestBody UsuarioDTOInsert usuario){

        Role role = rS.listid(usuario.getIdRol())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el rol con el id: "
                                        + usuario.getIdRol()
                        ));

        Familia familia = fS.listid(usuario.getIdFamilia())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la familia con el id: "
                                        + usuario.getIdFamilia()
                        ));

        Pais pais = pS.listid(usuario.getIdPais())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el pais con el id: "
                                        + usuario.getIdPais()
                        ));

        Users nuevo_users =
                modelMapper.map(usuario, Users.class);

        nuevo_users.setRoles(List.of(role)); // VERIFICAR
        nuevo_users.setFamilia(familia);
        nuevo_users.setPais(pais);

        uS.insert(nuevo_users);

        UsuarioDTOInsert responseDTO =
                modelMapper.map(nuevo_users, UsuarioDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo_users.getIdUsuario())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // CONSULTAR USUARIO por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOList> buscarid(@PathVariable("id") Long id){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        UsuarioDTOList responseDTO = modelMapper.map(users, UsuarioDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTOInsert> actualizar_usuario(@PathVariable("id")Long id, @Validated @RequestBody UsuarioDTOInsert dto){
        Users users = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        Familia familia = fS.listid(dto.getIdFamilia())
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        Pais pais = pS.listid(dto.getIdPais())
                .orElseThrow(()->new ResourceNotFoundException("No existe el rol"));

        Users users_actualizado = modelMapper.map(dto, Users.class);
        users_actualizado.setIdUsuario(id);
        users_actualizado.setFamilia(familia);
        users_actualizado.setPais(pais);
        uS.update(users_actualizado);
        UsuarioDTOInsert responseDTO = modelMapper.map(users_actualizado, UsuarioDTOInsert.class);
        return ResponseEntity.ok(responseDTO);

    }
    //
    @GetMapping("/IntegrantesFamilia/{idFamilia}")
    public ResponseEntity<List<UsuarioDTOList>> listarIntegrantes(@PathVariable long idFamilia){
        List<UsuarioDTOList> lista_usuarios = uS.list()
                .stream()
                .map(u->modelMapper.map(u, UsuarioDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }

    // ELIMINAR USUARIO de una familia
    // @PutMapping("/eliminarfamilia/{id}")
    // public
}
