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
    //HU17 Listar ingredientes vencidos
    public List<Item> findByfechaVencimientoBefore(LocalDate fechaActual);

    //HU17 Listar proximos a vencer Ordenados
    @Query(value = "SELECT i FROM Item i"
            + " WHERE i.fechaVencimiento BETWEEN :fechaActual AND :fechaLimite"
            + " ORDER BY i.fechaVencimiento ASC"
    )
    List<Item> findByfechaVencimientoBetween(
            @Param("fechaActual") LocalDate fechaActual,
            @Param("fechaLimite") LocalDate fechaLimite
    );

    //HU18 Listar alimentos con bajo Stock
    @Query(value = "SELECT i FROM Item i"
            + " WHERE i.cantidadDisposicion <= i.stockMinimo"
    )
    List<Item> findAlimentosBajoStock();




}
