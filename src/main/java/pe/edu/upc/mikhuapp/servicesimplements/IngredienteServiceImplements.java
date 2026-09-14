package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Ingrediente;
import pe.edu.upc.mikhuapp.repositories.IIngredienteRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredienteService;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServiceImplements implements IIngredienteService {

    @Autowired
    private IIngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> list() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Ingrediente insert(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @Override
    public Optional<Ingrediente> listid(Long id) {
        return ingredienteRepository.findById(id);
    }
}