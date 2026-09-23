package pe.edu.upc.mikhuapp.servicesinterfaces;

import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<Users> list(); // Listar usuarios

    public Optional<Users> findById(Long id); // Buscar usuario por ID

    public void insert(Users user); // Insertar un usuario

    public void delete(Long id); // Eliminar un usuario

    public Users update(Users user);

    public List<Users> listFamilyMembers(Long familyId);
}