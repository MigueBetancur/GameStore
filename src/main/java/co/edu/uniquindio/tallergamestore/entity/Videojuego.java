package co.edu.uniquindio.tallergamestore.entity;

import co.edu.uniquindio.tallergamestore.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se define máximo 100 carácteres y debe ser obligatorio
    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private Double precio;

    // Se define como único y obligatorio
    @Column(unique = true, nullable = false)
    private String codigoRegistro;

    // Se guarda en la BD como está definido en el Enum Género
    @Enumerated(EnumType.STRING)
    private Genero genero;

    // Relación de Videojuego con Desarrolladora - muchos videojuegos pertenecen a una desarrolladora
    @ManyToOne
    @JoinColumn(name = "desarrolladora_id")
    private Desarrolladora desarrolladora;

    // Auditoría - fechas automatizadas
    @Column(updatable = false) // La fecha de creación no puede ser modificada después de crearse
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    // No existe en la base de datos
    @Transient
    private Double precioConIva;

    // Gestión de fechas automáticamente
    @PrePersist
    public void antesDeGuardar() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void antesDeActualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Constructor
    public Videojuego() {}
}
