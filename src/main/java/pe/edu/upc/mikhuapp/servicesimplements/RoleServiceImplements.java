package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.repositories.IRoleRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplements implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleServiceImplements(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @Override
    public void insert(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}