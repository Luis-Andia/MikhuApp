package pe.edu.upc.mikhuapp.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;
import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {

    public void insert(Recipe recipe);

    public List<Recipe> list();

    public Optional<Recipe> listId(Long id);

    public void update(Recipe recipe);

    public void delete(Long id);

    public List<Recipe> listarPorIngrediente(String nombreIngrediente);
    public List<Recipe> findRecipe_nomRecipe(@Param("nomRecipe") String nomRecipe);
    public List<RecipeIngredient> consultarDetalleReceta(Long idRecipe);

}