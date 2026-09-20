package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mikhuapp.dtos.*;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private IRecetaService recetaService;

    @Autowired
    private IPaisService paisService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRecipeRepository iRecipeRepository;

    @Autowired
    private IRecetaService iRecetaService;
    @Autowired
    private IFamiliaService iFamiliaService;

    //HU23 LISTAR RECETAS
    @GetMapping("/listarReceta")
    public ResponseEntity<List<RecetaDTOList>> listarReceta() {
        List<RecetaDTOList> lista =iRecetaService.list()
                .stream()
                .map(receta->modelMapper.map(receta, RecetaDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
