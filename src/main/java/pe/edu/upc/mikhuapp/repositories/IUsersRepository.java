package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users,Long> {
    List<Users> findByFamilia_idFamilia(Long idFamilia);

    Optional<Users> findByNomUsuario(String nomUsuario);
}
