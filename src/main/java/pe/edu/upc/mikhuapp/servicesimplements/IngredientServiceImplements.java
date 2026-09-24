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
    private final IIngredientRepository iR;


    public IngredientServiceImplements(IIngredientRepository iR) {
        this.iR = iR;
    }

    @Override
    public List<Ingredient> list() {
        return iR.findAll();
    }

    @Override
    public void insert(Ingredient ingredient) {
        iR.save(ingredient);
    }

    @Override
    public Optional<Ingredient> listId(Long id) {
        return iR.findById(id);
    }

    @Override
    public Optional<Ingredient> findByNomIngredient(String nomIngredient) {
        return iR.findByNomIngredient(nomIngredient);
    }
}