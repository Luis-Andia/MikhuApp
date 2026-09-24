package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Ingredient;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, Long> {
}