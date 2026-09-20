package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.repositories.IFamiliaRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliaServiceImplements implements IFamiliaService {
    private final IFamiliaRepository fR;

    public FamiliaServiceImplements(IFamiliaRepository fR) {
        this.fR = fR;
    }

    @Override
    public List<Family> list() {
        return fR.findAll();
    }

    @Override
    public Family insert(Family f) {
        return fR.save(f);
    }

    @Override
    public Optional<Family> listid(Long id) {
        return fR.findById(id);
    }

    @Override
    public Family update(Family f) {
        return fR.save(f);
    }
}
