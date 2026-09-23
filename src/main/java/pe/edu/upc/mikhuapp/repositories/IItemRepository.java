package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;

import java.time.LocalDate;
import java.util.List;

public interface IItemRepository extends JpaRepository<Item, Long> {

    // HU16 Buscar item por nombre de ingrediente
    public List<Item> findByIngredient_IngredientNameContainingIgnoreCase(String name);

    // HU17 Listar ingredientes vencidos
    public List<Item> findByExpirationDateBefore(LocalDate currentDate);

    // HU17 Listar proximos a vencer
    public List<Item> findByExpirationDateBetween(
            LocalDate currentDate,
            LocalDate limitDate);

    // HU18 Listar alimentos con bajo Stock
    @Query("""
        SELECT i FROM Item i
            WHERE i.availableQuantity <= i.minimumStock
    """)
    List<Item> findLowStockItems();

    // HU22 Obtener ingredientes disponibles de una familia
    @Query("""
        SELECT DISTINCT i.ingredient
        FROM Item i
        WHERE i.family.idFamily = :idFamily
          AND i.availableQuantity > 0
    """)
    List<Ingredient> findAvailableIngredientsByFamilyId(Long idFamily);
}