package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.dtos.PaisDTO;
import pe.edu.upc.mikhuapp.entities.Pais;
import pe.edu.upc.mikhuapp.repositories.IPaisRepository;

import java.util.List;
import java.util.Optional;

public interface IPaisService {
    public void insert(Pais p);
    public List<Pais> list();
    public Optional<Pais> listID(Long id);
    public Pais update(Pais p);
    public void delete(Long id);
}
