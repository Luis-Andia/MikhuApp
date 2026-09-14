package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.HistorialConsumo;

import java.util.List;
import java.util.Optional;

public interface IHistorialConsumoService {

    HistorialConsumo insert(HistorialConsumo historialConsumo);

    List<HistorialConsumo> list();

    Optional<HistorialConsumo> listid(Long id);
}