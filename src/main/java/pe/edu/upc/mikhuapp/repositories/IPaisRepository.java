package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Pais;

@Repository
public interface IPaisRepository extends JpaRepository<Pais, Long> {

}
