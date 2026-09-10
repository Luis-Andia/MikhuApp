package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
}
