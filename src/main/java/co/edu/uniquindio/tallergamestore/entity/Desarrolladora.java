package co.edu.uniquindio.tallergamestore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class Desarrolladora {
    // Getters y Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El atributo debe ser: Obligatorio y Único
    @Column(nullable = false, unique = true)
    private String nombre;

    private String sitioWeb;

    // Relación de Desarrolladora con VideoJuego - una desarrolladora tiene muchos videojuegos
    // @JsonIgnore: evita ciclo infinito
    @JsonIgnore
    @OneToMany(mappedBy = "desarrolladora", cascade = CascadeType.ALL)
    private List<Videojuego> videojuegos;

    // Constructor
    public Desarrolladora() {}
}
