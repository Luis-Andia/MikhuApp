package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.RecipeIngredient;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeIngredientService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeIngredientServiceImplements implements IRecipeIngredientService {
    private final IRecipeIngredientService riS;

    public RecipeIngredientServiceImplements(IRecipeIngredientService riS) {
        this.riS = riS;
    }

    @Override
    public List<RecipeIngredient> list() {
        return riS.list();
    }

    @Override
    public void insert(RecipeIngredient ri) {
        riS.insert(ri);
    }

    @Override
    public void update(RecipeIngredient ri) {
        riS.update(ri);
    }

    @Override
    public Optional<RecipeIngredient> listId(Long id) {
        return riS.listId(id);
    }

    @Override
    public void delete(Long id) {
        riS.delete(id);
    }
}
