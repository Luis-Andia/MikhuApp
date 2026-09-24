package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Item;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    //HU Listar ingredientes vencidos
    public List<Item> findByDueDateBefore(LocalDate fechaActual);

    //HU Listar proximos a vencer Ordenados
    @Query(value = "SELECT i FROM Item i"
            + " WHERE i.dueDate BETWEEN :fechaActual AND :fechaLimite"
            + " ORDER BY i.dueDate ASC"
    )
    List<Item> findByDueDateBetween(
            @Param("fechaActual") LocalDate fechaActual,
            @Param("fechaLimite") LocalDate fechaLimite
    );

    //HU47 Listar items del inventario familiar
    @Query(value = "SELECT i FROM Item i"
            + " WHERE i.family.idFamily = :idFamily"
    )
    List<Item> findByFamilyId(
            @Param("idFamily") Long idFamily
    );

    //HU Listar alimentos con bajo Stock
    @Query(value = "SELECT i FROM Item i"
            + " WHERE i.amountAvailable <= i.minimumStock"
    )
    List<Item> findAlimentosBajoStock();
}
