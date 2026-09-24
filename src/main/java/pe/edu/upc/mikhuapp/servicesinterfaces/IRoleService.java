package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Role;
import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public List<Role> list();
    public void insert(Role r);
    public Optional<Role> listid(Long id);
    public Role update(Role r);
    public void delete(Long id);
}
