package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Consumption;

public interface IHistorialConsumoRepository extends JpaRepository<Consumption, Long> {
}