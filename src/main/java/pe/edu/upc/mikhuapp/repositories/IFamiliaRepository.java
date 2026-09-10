package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Familia;

@Repository
public interface IFamiliaRepository extends JpaRepository<Familia, Long> {

}
