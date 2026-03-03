package co.edu.uniquindio.tallergamestore.repository;

import co.edu.uniquindio.tallergamestore.entity.Videojuego;
import co.edu.uniquindio.tallergamestore.enums.Genero;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego,Long> {

    // Derived Query: Se busca un Videojuego por su género
    List<Videojuego> findByGenero(Genero genero);

    // Derived Query: Se busca un Videojuego por su género (ignorando mayúsculas/minúsculas)
    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);

    // Consulta JPQL: Se buscan videojuegos que estén dentro de un rango de precios
    @Query("SELECT v FROM Videojuego v WHERE v.precio BETWEEN :min AND :max")
    List<Videojuego> buscarPorRangoDePrecio(@Param("min") Double min, @Param("max") Double max);

    // Consulta Nativa: Obtener los últimos 5 videojuegos registrados ordenados por fechaCreacion de forma descendente
    @NativeQuery(value = "SELECT * FROM Videojuego ORDER BY fecha_creacion DESC LIMIT 5")
    List<Videojuego> obtenerUltimos5();
}
