package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Receta;

import java.util.List;
import java.util.Optional;

public interface IRecetaService {

    Receta insert(Receta receta);

    List<Receta> list();

    Optional<Receta> listid(Long id);
}