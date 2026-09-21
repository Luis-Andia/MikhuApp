package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Recipe;
import pe.edu.upc.mikhuapp.repositories.IRecipeRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplements implements IRecetaService {

    @Autowired
    private IRecipeRepository recetaRepository;

    @Override
    public Recipe insert(Recipe recipe) {
        return recetaRepository.save(recipe);
    }

    @Override
    public List<Recipe> list() {
        return recetaRepository.findAll();
    }

    @Override
    public Optional<Recipe> listid(Long id) {
        return recetaRepository.findById(id);
    }
}