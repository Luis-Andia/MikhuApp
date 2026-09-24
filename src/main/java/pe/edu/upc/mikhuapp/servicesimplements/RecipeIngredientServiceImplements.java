package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;
import pe.edu.upc.mikhuapp.repositories.IRecipeIngredientRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeIngredientService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeIngredientServiceImplements implements IRecipeIngredientService {
    private final IRecipeIngredientRepository riR;

    public RecipeIngredientServiceImplements(IRecipeIngredientRepository riR) {
        this.riR = riR;
    }

    @Override
    public List<RecipeIngredient> list() {
        return riR.findAll();
    }

    @Override
    public void insert(RecipeIngredient ri) {
        riR.save(ri);
    }

    @Override
    public void update(RecipeIngredient ri) {
        riR.save(ri);
    }

    @Override
    public Optional<RecipeIngredient> listId(Long id) {
        return riR.findById(id);
    }

    @Override
    public void delete(Long id) {
        riR.deleteById(id);
    }
}
