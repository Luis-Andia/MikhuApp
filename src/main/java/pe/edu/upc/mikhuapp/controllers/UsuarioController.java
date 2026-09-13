package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.mikhuapp.dtos.UsuarioDTO;
import pe.edu.upc.mikhuapp.dtos.UsuarioDTOList;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUsuarioService;

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

    //
}
