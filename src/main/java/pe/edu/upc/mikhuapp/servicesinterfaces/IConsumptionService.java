package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.util.List;
import java.util.Optional;

public interface IConsumptionService {

    Consumption insert(Consumption consumption);

    List<Consumption> list();

    Optional<Consumption> findById(Long id);

    List<Consumption> findByFamilyId(Long idFamily);
}