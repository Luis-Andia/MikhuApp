package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Item;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
