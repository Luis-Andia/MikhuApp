package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.repositories.ICountryRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplements implements ICountryService {
    private final ICountryRepository pR;

    public CountryServiceImplements(ICountryRepository pR) {
        this.pR = pR;
    }

    @Override
    public void insert(Country p) {
        pR.save(p);
    }

    @Override
    public List<Country> list() {
        return pR.findAll();
    }

    @Override
    public Optional<Country> listid(Long id) {
        return pR.findById(id);
    }

    @Override
    public Country update(Country p) {
        return pR.save(p);
    }

    @Override
    public void delete(Long id) {
        pR.deleteById(id);
    }
}
