package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.dtos.RecipeSuggestionDTO;
import pe.edu.upc.mikhuapp.entities.Recipe;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {

    Recipe insert(Recipe recipe);

    List<Recipe> list();

    Optional<Recipe> findById(Long id);

    List<RecipeSuggestionDTO> findPersonalizedRecipes(Long idFamily);
}