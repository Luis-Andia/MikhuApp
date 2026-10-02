package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.dtos.RecipeDetailDTO;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {


    // HU52 - Consultar detalle de receta
    @Query(value = "SELECT ri.* " +
            "FROM recipe_ingredients ri " +
            "INNER JOIN recipes r " +
            "ON ri.id_recipe = r.id_recipe " +
            "INNER JOIN ingredients i " +
            "ON ri.id_ingredient = i.id_ingredient " +
            "WHERE r.id_recipe = :idRecipe",
            nativeQuery = true)
    List<RecipeIngredient> consultarDetalleReceta(
            @Param("idRecipe") Long idRecipe);

    // HU53: Consultar receta por nombre
    @Query(value="SELECT * FROM recipes where nom_recipe ILIKE '%' || :nomRecipe || '%';", nativeQuery = true)
    public List<Recipe> findRecipe_nomRecipe(@Param("nomRecipe") String nomRecipe);

    // HU57: CONSULTAR RECETAS POR INGREDIENTE
    @Query(value = "SELECT r.* " +
            "FROM recipe_ingredients ri " +
            "INNER JOIN recipes r ON ri.id_recipe = r.id_recipe " +
            "INNER JOIN ingredients i ON ri.id_ingredient = i.id_ingredient " +
            "WHERE i.nom_ingredient = :nombreIngrediente",
            nativeQuery = true)
    public List<Recipe> findByIngrediente(@Param("nombreIngrediente") String nombreIngrediente);
}