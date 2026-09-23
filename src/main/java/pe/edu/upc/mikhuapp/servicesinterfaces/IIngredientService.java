package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

    List<Ingredient> list();

    Ingredient insert(Ingredient ingredient);

    Optional<Ingredient> findById(Long id);
}