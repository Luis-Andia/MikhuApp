package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

    public List<Ingredient> list();
    public void insert(Ingredient ingredient);
    public Optional<Ingredient> listId(Long id);
}
