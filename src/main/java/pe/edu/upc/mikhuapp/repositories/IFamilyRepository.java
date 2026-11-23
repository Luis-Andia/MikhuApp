package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mikhuapp.entities.Family;

import java.util.Optional;


@Repository
public interface IFamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findFamiliesByNomFamily(String nameFamily);
}
