package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Ingredient;
import pe.edu.upc.mikhuapp.repositories.IIngredientRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IIngredientService;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImplements implements IIngredientService {

    @Autowired
    private IIngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> list() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient insert(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }
}