package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Consumption;

import java.util.List;

public interface IConsumptionRepository extends JpaRepository<Consumption, Long> {

    List<Consumption> findByItem_Family_IdFamily(Long idFamily);
}