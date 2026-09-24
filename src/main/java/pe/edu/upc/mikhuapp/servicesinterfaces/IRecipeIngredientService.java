package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.RecipeIngredient;

import java.util.List;
import java.util.Optional;

public interface IRecipeIngredientService {
    public List<RecipeIngredient> list();
    public void insert(RecipeIngredient ri);
    public void update(RecipeIngredient ri);
    public Optional<RecipeIngredient> listId(Long id);
    public void delete(Long id);
}
