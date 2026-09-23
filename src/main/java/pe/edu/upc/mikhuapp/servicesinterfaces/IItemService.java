package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IItemService {

    public List<Item> list();

    public Item insert(Item item);

    public Optional<Item> findById(Long id);

    // HU16 Buscar item por nombre de ingrediente
    public List<Item> searchByName(String name);

    public List<Item> findExpiredItems(LocalDate currentDate);

    public List<Item> findItemsExpiringSoon(LocalDate currentDate, LocalDate limitDate);

    public List<Item> findLowStockItems();

    public void delete(Long id);

    // HU22 Obtener ingredientes disponibles de una familia
    public List<Ingredient> findAvailableIngredientsByFamilyId(Long idFamily);
}