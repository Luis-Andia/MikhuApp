package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.clients.SpoonacularClient;
import pe.edu.upc.mikhuapp.dtos.RecipeSuggestionDTO;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplements implements IRecipeService {

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private IItemService itemService;

    @Autowired
    private SpoonacularClient spoonacularClient;

    @Override
    public Recipe insert(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> list() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<RecipeSuggestionDTO> findPersonalizedRecipes(Long idFamily) {

        List<Ingredient> availableIngredients =
                itemService.findAvailableIngredientsByFamilyId(idFamily);

        if (availableIngredients.isEmpty()) {
            return List.of();
        }

        List<String> ingredientNames = availableIngredients.stream()
                .map(Ingredient::getIngredientName)
                .toList();

        return spoonacularClient.searchByIngredients(ingredientNames);
    }
}