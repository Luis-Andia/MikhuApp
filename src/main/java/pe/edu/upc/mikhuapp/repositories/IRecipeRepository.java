package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.dtos.RecipeDetailDTO;
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

    // HU52 CONSULTAR DETALLE DE RECETA
    @Query("""
    SELECT new pe.edu.upc.mikhuapp.dtos.RecipeDetailDTO(
        r.idRecipe,
        r.nomRecipe,
        r.calories,
        r.difficulty,
        i.idIngredient,
        i.nomIngredient,
        ri.requestedQuantity
    )
    FROM RecipeIngredient ri
    JOIN ri.recipe r
    JOIN ri.ingredient i
    WHERE r.idRecipe = :idRecipe
""")
    List<RecipeDetailDTO> consultarDetalleReceta(
            @Param("idRecipe") Long idRecipe);
}