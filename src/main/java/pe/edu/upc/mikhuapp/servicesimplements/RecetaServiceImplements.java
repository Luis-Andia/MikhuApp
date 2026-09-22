package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.clients.SpoonacularClient;
import pe.edu.upc.mikhuapp.dtos.RecetaSugeridaDTO;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.entities.Receta;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.repositories.IRecetaRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImplements implements IRecetaService {

    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private IItemService itemService;

    @Autowired
    private SpoonacularClient spoonacularClient;

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

    // HU22 - Buscar recetas personalizadas según los ingredientes disponibles
    @Override
    public List<RecetaSugeridaDTO> buscarRecetasPersonalizadas(Long idFamilia) {

        List<Item> itemsDisponibles =
                itemService.listarDisponiblesPorFamilia(idFamilia);

        if (itemsDisponibles.isEmpty()) {
            throw new ResourceNotFoundException(
                    "La familia no tiene alimentos disponibles");
        }

        List<String> ingredientesDisponibles = itemsDisponibles.stream()
                .map(item -> item.getIngrediente().getNomIngrediente())
                .distinct()
                .toList();

        List<RecetaSugeridaDTO> recetas =
                spoonacularClient.buscarPorIngredientes(ingredientesDisponibles);

        if (recetas.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontraron recetas compatibles con los ingredientes disponibles");
        }

        return recetas;
    }
}