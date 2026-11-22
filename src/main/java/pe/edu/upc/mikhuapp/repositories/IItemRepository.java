package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Item;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    //HU50 Listar ingredientes vencidos
    // HU50 Listar ingredientes vencidos
    @Query(value = "SELECT * FROM items "
            + "WHERE due_date < :fechaActual "
            + "AND id_family = :idFamily "
            + "ORDER BY due_date ASC",
            nativeQuery = true)
    List<Item> findByDueDateBeforeAndFamilyId(
            @Param("fechaActual") LocalDate fechaActual,
            @Param("idFamily") Long idFamily);

    //HU50 Listar proximos a vencer Ordenados
    @Query(value = "SELECT i.* FROM items i " +
            "INNER JOIN ingredients ing ON i.id_ingredient = ing.id_ingredient " +
            "WHERE i.id_family = :idFamily " +
            "AND i.due_date BETWEEN :fechaActual AND :fechaLimite " +
            "ORDER BY i.due_date ASC",
            nativeQuery = true)
    List<Item> findByDueDateBetween(
            @Param("idFamily") Long idFamily,
            @Param("fechaActual") LocalDate fechaActual,
            @Param("fechaLimite") LocalDate fechaLimite);

    //HU47 Listar items del inventario familiar
    @Query(value = "SELECT i.id_item, ing.nom_ingredient, i.amount_available, " +
            "i.purchase_date, i.due_date, i.minimum_stock " +
            "FROM items i INNER JOIN ingredients ing " +
            "ON i.id_ingredient = ing.id_ingredient " +
            "WHERE i.id_family = :idFamily", nativeQuery = true)
    List<Object[]> findByFamilyId(
            @Param("idFamily") Long idFamily
    );

    // HU49 Listar alimentos con bajo Stock
    @Query(value = "SELECT i.id_item AS idItem, "
            + "f.nom_family AS nomFamily, "
            + "ing.nom_ingredient AS nomIngredient, "
            + "i.amount_available AS amountAvailable, "
            + "i.purchase_date AS purchaseDate, "
            + "i.due_date AS dueDate, "
            + "i.minimum_stock AS minimumStock "
            + "FROM items i "
            + "INNER JOIN families f ON i.id_family = f.id_family "
            + "INNER JOIN ingredients ing ON i.id_ingredient = ing.id_ingredient "
            + "WHERE i.amount_available <= i.minimum_stock "
            + "AND i.id_family = :idFamily", nativeQuery = true)
    List<Object[]> findAlimentosBajoStock(@Param("idFamily") Long idFamily);

    // HU48 Buscar item por nombre
    List<Item> findByIngredient_NomIngredientContainingIgnoreCase(String nombre);

    //HU58 Listar cantidad de ingredientes disponibles por familia
    @Query(value = "SELECT " +
            "i.id_family, " +
            "ing.id_ingredient, " +
            "ing.nom_ingredient AS ingrediente, " +
            "SUM(i.amount_available) AS cantidad_disponible " +
            "FROM items i " +
            "INNER JOIN ingredients ing ON ing.id_ingredient = i.id_ingredient " +
            "WHERE i.id_family = :idFamily " +
            "GROUP BY i.id_family, ing.id_ingredient, ing.nom_ingredient " +
            "ORDER BY ing.nom_ingredient",
            nativeQuery = true)
    List<Object[]> availabilityOfIngredientsByFamily(
            @Param("idFamily") Long idFamily);
}
