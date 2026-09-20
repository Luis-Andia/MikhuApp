package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Recipe;

//HU23 Listar Receta
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {
}