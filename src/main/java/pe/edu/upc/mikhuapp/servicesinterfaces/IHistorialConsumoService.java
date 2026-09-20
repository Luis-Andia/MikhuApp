package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.util.List;
import java.util.Optional;

public interface IHistorialConsumoService {

    Consumption insert(Consumption consumption);

    List<Consumption> list();

    Optional<Consumption> listid(Long id);
}