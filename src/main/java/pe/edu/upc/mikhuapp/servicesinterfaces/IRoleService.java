package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public List<Role> list();

    public void insert(Role role);

    public Optional<Role> findById(Long id);
}