package pe.edu.upc.mikhuapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.mikhuapp.entities.Receta;

import java.util.List;

//HU23 Listar Receta
public interface IRecetaRepository extends JpaRepository<Receta, Long> {
}