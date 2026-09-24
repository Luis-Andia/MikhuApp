package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeIngredientService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientController {
    private final IRecipeIngredientService riS;
    private final IIngredientService iS;
    private final IRecipeService rS;
    private final ModelMapper modelMapper;


    public RecipeIngredientController(IRecipeIngredientService riS, IIngredientService iS, IRecipeService rS, ModelMapper modelMapper) {
        this.riS = riS;
        this.iS = iS;
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    // Listar

    // Registar

    // Buscar por ID
}
