package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Family;

import java.util.List;
import java.util.Optional;

public interface IFamilyService {

    public List<Family> list();

    public Family insert(Family family);

    public Optional<Family> findById(Long id);

    public Family update(Family family);
}