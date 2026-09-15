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
import pe.edu.upc.mikhuapp.entities.Rol;
import pe.edu.upc.mikhuapp.entities.Usuario;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUsuarioService;

import java.net.URI;
import java.util.List;

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

        Rol rol = rS.listid(usuario.getIdRol())
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

        Usuario nuevo_usuario =
                modelMapper.map(usuario, Usuario.class);

        nuevo_usuario.setRol(rol);
        nuevo_usuario.setFamilia(familia);
        nuevo_usuario.setPais(pais);

        uS.insert(nuevo_usuario);

        UsuarioDTOInsert responseDTO =
                modelMapper.map(nuevo_usuario, UsuarioDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo_usuario.getIdUsuario())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // CONSULTAR USUARIO por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOList> buscarid(@PathVariable("id") Long id){
        Usuario usuario = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        UsuarioDTOList responseDTO = modelMapper.map(usuario, UsuarioDTOList.class);
        return ResponseEntity.ok(responseDTO);
    }

    // ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTOInsert> actualizar_usuario(@PathVariable("id")Long id, @Validated @RequestBody UsuarioDTOInsert dto){
        Usuario usuario = uS.listId(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));
        Familia familia = fS.listid(dto.getIdFamilia())
                .orElseThrow(()->new ResourceNotFoundException("No existe la familia"));
        Pais pais = pS.listid(dto.getIdPais())
                .orElseThrow(()->new ResourceNotFoundException("No existe el rol"));
        Rol rol = rS.listid(dto.getIdRol())
                .orElseThrow(()->new ResourceNotFoundException("No existe el rol"));

        Usuario usuario_actualizado = modelMapper.map(dto, Usuario.class);
        usuario_actualizado.setIdUsuario(id);
        usuario_actualizado.setFamilia(familia);
        usuario_actualizado.setPais(pais);
        usuario_actualizado.setRol(rol);

        uS.update(usuario_actualizado);
        UsuarioDTOInsert responseDTO = modelMapper.map(usuario_actualizado, UsuarioDTOInsert.class);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping("/IntegrantesFamilia/{idFamilia}")
    public ResponseEntity<List<UsuarioDTOList>> listarIntegrantes(@PathVariable long idFamilia){
        List<UsuarioDTOList> lista_usuarios = uS.list()
                .stream()
                .map(u->modelMapper.map(u, UsuarioDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_usuarios);
    }
}
