package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.repositories.IPaisRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;

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
}
