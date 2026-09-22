package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    public void insert(Country p);
    public List<Country> list();
    public Optional<Country> listid(Long id);
    public Country update(Country p);
    public void delete(Long id);
}
