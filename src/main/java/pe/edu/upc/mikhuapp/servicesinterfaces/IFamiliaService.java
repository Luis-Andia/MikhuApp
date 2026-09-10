package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Familia;

import java.util.List;
import java.util.Optional;

public interface IFamiliaService {
    public List<Familia> list();
    public Familia insert(Familia f);
    public Optional<Familia> listid(Long id);
}
