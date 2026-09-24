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
    private final IRecipeService rS;
    private final ModelMapper modelMapper;
    private final ICountryService cS;
    private final IFamilyService fS;

    public RecipeController(IRecipeService rS, ModelMapper modelMapper, ICountryService cS, IFamilyService fS) {
        this.rS = rS;
        this.modelMapper = modelMapper;
        this.cS = cS;
        this.fS = fS;
    }

    //HU23 LISTAR RECETAS
    @GetMapping("/listarReceta")
    public ResponseEntity<List<RecipeDTOList>> listarReceta() {
        List<RecipeDTOList> lista = rS.list()
                .stream()
                .map(receta->modelMapper.map(receta, RecipeDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
