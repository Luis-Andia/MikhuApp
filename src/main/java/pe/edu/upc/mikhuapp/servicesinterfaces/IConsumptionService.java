package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsumptionService {

    public Consumption insert(Consumption consumption);
    public List<Consumption> list();
    public Optional<Consumption> listid(Long id);
    public List<Object[]>ListMostConsumedIngredients();

    // HU55: Consultar historial de consumo por IdFamily
    public List<Consumption> consultarPorFamilia(Long idFamily);

    void update(Consumption consumption);
    void delete (Long id);
    public List<Consumption> consultarPorFecha(
            LocalDate fechaInicio,
            LocalDate fechaFin);
}