package pe.edu.upc.mikhuapp.servicesinterfaces;


import pe.edu.upc.mikhuapp.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> list(); // Listar usuarios
    public Optional<Usuario> listId(Long id); // Buscar usuario por ID
    public void insert(Usuario u); // Insertar un usuario
    public void delete(Long id); // Eliminar un usuario
    public Usuario update(Usuario u);
    public List<Usuario> listarIntegrantes(Long idFamilia);
}
