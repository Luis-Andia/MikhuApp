package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.HistorialConsumo;

import java.util.List;

public interface IHistorialConsumoRepository extends JpaRepository<HistorialConsumo, Long> {

    // HU20 Consultar historial de consumo por familia
    List<HistorialConsumo> findByItem_Familia_IdFamilia(Long idFamilia);

}