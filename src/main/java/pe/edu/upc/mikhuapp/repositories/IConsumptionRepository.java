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
    @Query(value="SELECT i.id_item, ing.nom_ingredient, COUNT(c.id_consumption) as cantidad from consumption c \n" +
            "INNER JOIN Item i on c.id_item = i.id_item\n" +
            "INNER JOIN Ingredient ing on i.id_ingredient = ing.id_ingredient \n" +
            "GROUP BY i.id_item, ing.nom_ingredient\n" +
            "ORDER BY cantidad DESC;", nativeQuery = true)
    public List<Object[]>ListMostConsumedIngredients();

    // HU56 - Consultar ingredientes consumidos por fecha
    @Query("SELECT c FROM Consumption c " +
            "WHERE c.consumptionDate >= :fechaInicio " +
            "AND c.consumptionDate < :fechaFin")
    public List<Consumption> consultarPorFecha(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin);
}