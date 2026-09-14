package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.HistorialConsumo;

public interface IHistorialConsumoRepository extends JpaRepository<HistorialConsumo, Long> {
}