package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Familia;
import pe.edu.upc.mikhuapp.repositories.IFamiliaRepository;
import pe.edu.upc.mikhuapp.repositories.IPaisRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;

import java.util.List;

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
}
