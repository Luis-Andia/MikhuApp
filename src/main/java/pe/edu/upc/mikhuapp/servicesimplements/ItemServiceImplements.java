package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.repositories.IItemRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplements implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Override
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    // HU16 Buscar item por nombre de ingrediente
    @Override
    public List<Item> searchByName(String name) {
        return itemRepository.findByIngredient_IngredientNameContainingIgnoreCase(name);
    }

    @Override
    public List<Item> findExpiredItems(LocalDate currentDate) {
        return itemRepository.findByExpirationDateBefore(currentDate);
    }

    @Override
    public List<Item> findItemsExpiringSoon(LocalDate currentDate, LocalDate limitDate) {
        return itemRepository.findByExpirationDateBetween(currentDate, limitDate);
    }

    @Override
    public List<Item> findLowStockItems() {
        return itemRepository.findLowStockItems();
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    // HU22 Obtener ingredientes disponibles de una familia
    @Override
    public List<Ingredient> findAvailableIngredientsByFamilyId(Long idFamily) {
        return itemRepository.findAvailableIngredientsByFamilyId(idFamily);
    }
}