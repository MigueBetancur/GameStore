package co.edu.uniquindio.tallergamestore.repository;

import co.edu.uniquindio.tallergamestore.entity.Desarrolladora;
import org.springframework.data.jpa.repository.JpaRepository;

// Nos permite utilizar save, finfById, findAll, etc
public interface DesarrolladoraRepository extends JpaRepository<Desarrolladora, Long> {
}
