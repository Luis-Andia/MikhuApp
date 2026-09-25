package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.dtos.RecipeDetailDTO;
import pe.edu.upc.mikhuapp.entities.Recipe;
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

    List<RecipeDetailDTO> consultarDetalleReceta(Long idRecipe);
}