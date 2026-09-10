package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.mikhuapp.dtos.FamiliaDTOList;
import pe.edu.upc.mikhuapp.repositories.IFamiliaRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;

import java.util.List;

@RestController
@RequestMapping("/api/familia")
public class FamiliaController {
    // Inyecciones
    private final IFamiliaService fS;
    private final ModelMapper modelMapper;

    public FamiliaController(IFamiliaService fS, ModelMapper modelMapper) {
        this.fS = fS;
        this.modelMapper = modelMapper;
    }

    // METODOS

    // LISTAR
    @GetMapping
    public ResponseEntity<List<FamiliaDTOList>> listar(){
        List<FamiliaDTOList> lista_familias = fS.list()
                .stream()
                .map(f -> modelMapper.map(f, FamiliaDTOList.class))
                .toList();
        return ResponseEntity.ok(lista_familias);
    }
}
