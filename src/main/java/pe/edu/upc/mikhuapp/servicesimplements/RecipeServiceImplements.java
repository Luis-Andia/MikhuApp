package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecipeService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplements implements IRecipeService {

    private final IRecipeRepository rR;

    public RecipeServiceImplements(IRecipeRepository rR) {
        this.rR = rR;
    }


    @Override
    public void insert(Recipe recipe) {
        rR.save(recipe);
    }

    @Override
    public List<Recipe> list() {
        return rR.findAll();
    }

    @Override
    public Optional<Recipe> listId(Long id) {
        return rR.findById(id);
    }
}