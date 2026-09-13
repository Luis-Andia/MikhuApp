package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Familia;
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
    public List<Familia> list() {
        return fR.findAll();
    }

    @Override
    public Familia insert(Familia f) {
        return fR.save(f);
    }

    @Override
    public Optional<Familia> listid(Long id) {
        return fR.findById(id);
    }

    @Override
    public Familia update(Familia f) {
        return fR.save(f);
    }
}
