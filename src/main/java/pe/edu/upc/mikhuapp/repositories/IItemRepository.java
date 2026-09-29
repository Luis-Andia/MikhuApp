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
    //HU Listar ingredientes vencidos
    public List<Item> findByDueDateBefore(LocalDate fechaActual);

    //HU50 Listar proximos a vencer Ordenados
    @Query(value = "SELECT * FROM items " +
            "WHERE \"id_family\" = :idFamily " +
            "AND \"due_date\" BETWEEN :fechaActual AND :fechaLimite " +
            "ORDER BY \"due_date\" ASC",
            nativeQuery = true)
    List<Item> findByDueDateBetween(
            @Param("idFamily") Long idFamily,
            @Param("fechaActual") LocalDate fechaActual,
            @Param("fechaLimite") LocalDate fechaLimite);

    //HU47 Listar items del inventario familiar
    @Query(value = "SELECT i.idItem, ing.nomIngredient, i.amountAvailable, " +
            "i.purchaseDate, i.dueDate, i.minimumStock " +
            "FROM Item i JOIN i.ingredient ing " +
            "WHERE i.family.idFamily = :idFamily")
    List<Object[]> findByFamilyId(
            @Param("idFamily") Long idFamily
    );

    //HU49 Listar alimentos con bajo Stock
    @Query(value = "select * from items"
            + " WHERE amount_available <= minimum_stock", nativeQuery = true)
    List<Item> findAlimentosBajoStock();
}
