package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.repositories.IFamilyRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyServiceImplements implements IFamilyService {

    private final IFamilyRepository familyRepository;

    public FamilyServiceImplements(IFamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public List<Family> list() {
        return familyRepository.findAll();
    }

    @Override
    public Family insert(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Optional<Family> findById(Long id) {
        return familyRepository.findById(id);
    }

    @Override
    public Family update(Family family) {
        return familyRepository.save(family);
    }
}