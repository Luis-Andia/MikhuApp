package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.mikhuapp.entities.Item;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface IItemRepository extends JpaRepository<Item, Long> {
    //HU17 Listar ingredientes vencidos
    public List<Item> findByfechaVencimientoBefore(LocalDate fechaActual);

    //HU17 Listar proximos a vencer
    public List<Item> findByfechaVencimientoBetween(LocalDate fechaActual, LocalDate fechaLimite);

    //HU18 Listar alimentos con bajo Stock
    @Query("""
        SELECT i FROM Item i
            WHERE i.cantidadDisposicion <= i.stockMinimo
    """)
    List<Item> findAlimentosBajoStock();


}
