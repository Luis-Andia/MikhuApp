package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {

    List<Item> list();

    Item insert(Item item);

    Optional<Item> listid(Long id);
}