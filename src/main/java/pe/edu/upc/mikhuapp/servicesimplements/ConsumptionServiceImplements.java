package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Consumption;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.repositories.IConsumptionRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IConsumptionService;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionServiceImplements implements IConsumptionService {
    private final IConsumptionRepository cR;

    public ConsumptionServiceImplements(IConsumptionRepository cR) {
        this.cR = cR;
    }


    @Override
    public Consumption insert(Consumption consumption) {
        return cR.save(consumption);
    }

    @Override
    public List<Consumption> list() {
        return cR.findAll();
    }

    @Override
    public Optional<Consumption> listid(Long id) {
        return cR.findById(id);
    }

    @Override
    public List<Object[]> ListMostConsumedIngredients() {
        return cR.ListMostConsumedIngredients();
    }

    @Override
    public void update(Consumption consumption) {
        cR.save(consumption);
    }

    @Override
    public void delete(Long id) {
        cR.deleteById(id);
    }
}