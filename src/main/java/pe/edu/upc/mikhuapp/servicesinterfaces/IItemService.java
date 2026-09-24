package pe.edu.upc.mikhuapp.servicesinterfaces;

import org.springframework.cglib.core.Local;
import pe.edu.upc.mikhuapp.entities.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface   IItemService {

    public List<Item> list();

    public Item insert(Item item);

    public Optional<Item> listid(Long id);

    public List<Item> listarVencidos(LocalDate fechaActual);

    public List<Item> listarProximosVencer(LocalDate fechaActual, LocalDate fechaLimite);

    public List<Item> listarAlimentoBajoStock();

    public List<Item> listarItemsPorFamilia(Long idFamily);

    public void delete(Long id);

    public List<Item> findByfechaVencimientoBefore(LocalDate fechaActual);
    public List<Item> findByfechaVencimientoBetween(LocalDate fechaActual, LocalDate fechaLimite);
}