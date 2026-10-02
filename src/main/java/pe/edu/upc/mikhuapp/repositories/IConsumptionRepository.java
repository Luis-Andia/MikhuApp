package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Consumption;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IConsumptionRepository extends JpaRepository<Consumption, Long> {
    // HU51: Listar alimentos mas consumidos
    @Query(value="SELECT i.id_item, ing.nom_ingredient, COUNT(c.id_consumption) as cantidad from consumptions c\n" +
            "INNER JOIN items i on c.id_item = i.id_item\n" +
            "INNER JOIN ingredients ing on i.id_ingredient = ing.id_ingredient\n" +
            "WHERE i.id_family = :idFamily\n" +
            "GROUP BY i.id_item, ing.nom_ingredient\n" +
            "ORDER BY cantidad DESC;", nativeQuery = true)
    public List<Object[]>ListMostConsumedIngredients(@Param("idFamily") Long idFamily);

    // HU55: Consultar historial de consumo por IdFamily
    @Query(value = "SELECT c.* " +
            "FROM consumptions c " +
            "INNER JOIN items i ON c.id_item = i.id_item " +
            "WHERE i.id_family = :idFamily",
            nativeQuery = true)
    List<Consumption> consultarPorFamilia(
            @Param("idFamily") Long idFamily);

    // HU56 - Consultar ingredientes consumidos por fecha
    @Query(value = "SELECT " +
            "c.id_consumption, " +
            "c.consumption_date, " +
            "ing.nom_ingredient " +
            "FROM consumptions c " +
            "INNER JOIN items i ON c.id_item = i.id_item " +
            "INNER JOIN ingredients ing ON i.id_ingredient = ing.id_ingredient " +
            "WHERE c.consumption_date >= :fechaInicio " +
            "AND c.consumption_date < :fechaFin " +
            "AND i.id_family = :idFamily",
            nativeQuery = true)
    List<Object[]> consultarPorFecha(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("idFamily") Long idFamily);
}