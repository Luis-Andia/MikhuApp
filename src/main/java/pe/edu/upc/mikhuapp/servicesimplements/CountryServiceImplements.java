package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.repositories.ICountryRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplements implements ICountryService {

    private final ICountryRepository countryRepository;

    public CountryServiceImplements(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void insert(Country country) {
        countryRepository.save(country);
    }

    @Override
    public List<Country> list() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country update(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}