package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mikhuapp.dtos.*;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecipeController {

    @Autowired
    private IRecipeService recetaService;

    @Autowired
    private ICountryService paisService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRecipeRepository iRecipeRepository;

    @Autowired
    private IRecipeService iRecipeService;
    @Autowired
    private IFamilyService iFamilyService;

    //HU23 LISTAR RECETAS
    @GetMapping("/listarReceta")
    public ResponseEntity<List<RecipeDTOList>> listarReceta() {
        List<RecipeDTOList> lista = iRecipeService.list()
                .stream()
                .map(receta->modelMapper.map(receta, RecipeDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
