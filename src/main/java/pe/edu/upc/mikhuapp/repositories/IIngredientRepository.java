package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import java.util.Optional;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByNomIngredient(String nomIngredient);
}