package pe.edu.upc.mikhuapp.servicesinterfaces;


import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<Users> list(); // Listar usuarios
    public Optional<Users> listId(Long id); // Buscar usuario por ID
    public void insert(Users u); // Insertar un usuario
    public void delete(Long id); // Eliminar un usuario
    public Users update(Users u);
    public List<Users> listarIntegrantes(Long idFamilia);
}
