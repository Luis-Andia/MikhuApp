package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Ingrediente;

public interface IIngredienteRepository extends JpaRepository<Ingrediente, Long> {
}