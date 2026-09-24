package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Consumption;

import java.util.List;
import java.util.Optional;

public interface IConsumptionService {

    public Consumption insert(Consumption consumption);
    public List<Consumption> list();
    public Optional<Consumption> listid(Long id);
    public List<Object[]>ListMostConsumedIngredients();
}