package pe.edu.upc.mikhuapp.servicesinterfaces;

import org.springframework.cglib.core.Local;
import pe.edu.upc.mikhuapp.entities.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface   IItemService {

    List<Item> list();

    Item insert(Item item);

    Optional<Item> listid(Long id);

    List<Item> buscarPorNombre(String nombre);

    List<Item> listarDisponiblesPorFamilia(Long idFamilia);

    public List<Item> listarVencidos(LocalDate fechaActual);

    public List<Item> listarProximosVencer(LocalDate fechaActual, LocalDate fechaLimite);

    public List<Item> listarAlimentoBajoStock();
}