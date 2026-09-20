package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Ingredient;

public interface IIngredienteRepository extends JpaRepository<Ingredient, Long> {
}