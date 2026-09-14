package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Receta;
import pe.edu.upc.mikhuapp.repositories.IRecetaRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImplements implements IRecetaService {

    @Autowired
    private IRecetaRepository recetaRepository;

    @Override
    public Receta insert(Receta receta) {
        return recetaRepository.save(receta);
    }

    @Override
    public List<Receta> list() {
        return recetaRepository.findAll();
    }

    @Override
    public Optional<Receta> listid(Long id) {
        return recetaRepository.findById(id);
    }
}