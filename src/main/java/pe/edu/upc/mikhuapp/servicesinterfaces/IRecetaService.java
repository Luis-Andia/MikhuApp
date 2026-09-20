package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Recipe;

import java.util.List;
import java.util.Optional;

public interface IRecetaService {

    Recipe insert(Recipe recipe);

    List<Recipe> list();

    Optional<Recipe> listid(Long id);
}