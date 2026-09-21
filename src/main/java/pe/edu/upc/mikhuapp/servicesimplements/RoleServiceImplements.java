package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.repositories.IRoleRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplements implements IRolService {
    private final IRoleRepository rR;

    public RoleServiceImplements(IRoleRepository rR) {
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
