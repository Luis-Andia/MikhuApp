package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Recipe;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {

    // HU57 CONSULTAR RECETAS POR INGREDIENTE
    @Query(value = "SELECT ri.recipe FROM RecipeIngredient ri"
            + " JOIN ri.ingredient i"
            + " WHERE i.nomIngredient = :nombreIngrediente"
    )
    List<Recipe> findByIngrediente(
            @Param("nombreIngrediente") String nombreIngrediente
    );
}