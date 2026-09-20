package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Consumption;
import pe.edu.upc.mikhuapp.repositories.IHistorialConsumoRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IHistorialConsumoService;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialConsumoServiceImplements implements IHistorialConsumoService {

    @Autowired
    private IHistorialConsumoRepository historialConsumoRepository;

    @Override
    public Consumption insert(Consumption consumption) {
        return historialConsumoRepository.save(consumption);
    }

    @Override
    public List<Consumption> list() {
        return historialConsumoRepository.findAll();
    }

    @Override
    public Optional<Consumption> listid(Long id) {
        return historialConsumoRepository.findById(id);
    }
}