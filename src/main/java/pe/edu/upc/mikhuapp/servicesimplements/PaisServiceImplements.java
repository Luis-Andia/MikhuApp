package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.repositories.IPaisRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImplements implements IPaisService {
    private final IPaisRepository pR;

    public PaisServiceImplements(IPaisRepository pR) {
        this.pR = pR;
    }

    @Override
    public void insert(Pais p) {
        pR.save(p);
    }

    @Override
    public List<Pais> list() {
        return pR.findAll();
    }

    @Override
    public Optional<Pais> listid(Long id) {
        return pR.findById(id);
    }

    @Override
    public Pais update(Pais p) {
        return pR.save(p);
    }

    @Override
    public void delete(Long id) {
        pR.deleteById(id);
    }
}
