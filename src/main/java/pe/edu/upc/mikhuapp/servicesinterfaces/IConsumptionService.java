package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsumptionService {

    public Consumption insert(Consumption consumption);
    public List<Consumption> list();
    public Optional<Consumption> listid(Long id);

    // HU51: Listar alimentos mas consumidos
    public List<Object[]>ListMostConsumedIngredients(Long idFamily);

    // HU55: Consultar historial de consumo por IdFamily
    public List<Consumption> consultarPorFamilia(Long idFamily);

    void update(Consumption consumption);
    void delete (Long id);
    public List<Consumption> consultarPorFecha(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);
}