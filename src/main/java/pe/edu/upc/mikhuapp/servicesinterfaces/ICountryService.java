package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {

    public void insert(Country country);

    public List<Country> list();

    public Optional<Country> findById(Long id);

    public Country update(Country country);

    public void delete(Long id);
}