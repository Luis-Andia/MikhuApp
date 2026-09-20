package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.repositories.IRolRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImplements implements IRolService {
    private final IRolRepository rR;

    public RolServiceImplements(IRolRepository rR) {
        this.rR = rR;
    }

    @Override
    public List<Role> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Role r) {
        rR.save(r);
    }

    @Override
    public Optional<Role> listid(Long id) {
        return rR.findById(id);
    }
}
