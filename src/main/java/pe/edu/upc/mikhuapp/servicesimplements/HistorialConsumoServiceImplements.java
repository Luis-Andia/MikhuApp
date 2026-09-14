package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.HistorialConsumo;
import pe.edu.upc.mikhuapp.repositories.IHistorialConsumoRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IHistorialConsumoService;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialConsumoServiceImplements implements IHistorialConsumoService {

    @Autowired
    private IHistorialConsumoRepository historialConsumoRepository;

    @Override
    public HistorialConsumo insert(HistorialConsumo historialConsumo) {
        return historialConsumoRepository.save(historialConsumo);
    }

    @Override
    public List<HistorialConsumo> list() {
        return historialConsumoRepository.findAll();
    }

    @Override
    public Optional<HistorialConsumo> listid(Long id) {
        return historialConsumoRepository.findById(id);
    }
}