package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Family;

import java.util.List;
import java.util.Optional;

public interface IFamiliaService {
    public List<Family> list();
    public Family insert(Family f);
    public Optional<Family> listid(Long id);
    public Family update(Family f);
}
