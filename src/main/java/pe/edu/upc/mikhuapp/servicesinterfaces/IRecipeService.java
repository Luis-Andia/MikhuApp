package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {

    void insert(Recipe recipe);

    List<Recipe> list();

    Optional<Recipe> listId(Long id);

    void update(Recipe recipe);

    void delete(Long id);

    List<Recipe> listarPorIngrediente(String nombreIngrediente);
}