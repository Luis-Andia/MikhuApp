package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Ingrediente;

import java.util.List;
import java.util.Optional;

public interface IIngredienteService {

    List<Ingrediente> list();

    Ingrediente insert(Ingrediente ingrediente);

    Optional<Ingrediente> listid(Long id);
}
