
package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.RecipeIngredient;

import java.util.List;
import java.util.Optional;

public interface IRecipeIngredientService {

    List<RecipeIngredient> list();

    List<RecipeIngredient> listByRecipeId(Long recipeId);

    void insert(RecipeIngredient ri);

    void update(RecipeIngredient ri);

    Optional<RecipeIngredient> listId(Long id);

    void delete(Long id);

}
