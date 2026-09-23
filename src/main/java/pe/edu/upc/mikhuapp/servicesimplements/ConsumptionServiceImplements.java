package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Consumption;
import pe.edu.upc.mikhuapp.repositories.IConsumptionRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IConsumptionService;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionServiceImplements implements IConsumptionService {

    @Autowired
    private IConsumptionRepository consumptionRepository;

    @Override
    public Consumption insert(Consumption consumption) {
        return consumptionRepository.save(consumption);
    }

    @Override
    public List<Consumption> list() {
        return consumptionRepository.findAll();
    }

    @Override
    public Optional<Consumption> findById(Long id) {
        return consumptionRepository.findById(id);
    }

    @Override
    public List<Consumption> findByFamilyId(Long idFamily) {
        return consumptionRepository.findByItem_Family_IdFamily(idFamily);
    }
}