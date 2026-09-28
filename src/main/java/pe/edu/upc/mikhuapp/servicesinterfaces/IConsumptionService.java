package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsumptionService {

    public Consumption insert(Consumption consumption);
    public List<Consumption> list();
    public Optional<Consumption> listid(Long id);
    public List<Object[]>ListMostConsumedIngredients();
    void update(Consumption consumption);
    void delete (Long id);
    public List<Consumption> consultarPorFecha(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);
}