package co.edu.uniquindio.tallergamestore.service;

import co.edu.uniquindio.tallergamestore.entity.Desarrolladora;
import co.edu.uniquindio.tallergamestore.exception.ResourceNotFoundException;
import co.edu.uniquindio.tallergamestore.repository.DesarrolladoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Se define esta clase como un servicio
public class DesarrolladoraService {

    // Spring inyecta el repositorio automáticamente
    private final DesarrolladoraRepository desarrolladoraRepository;

    public DesarrolladoraService(DesarrolladoraRepository desarrolladoraRepository) {
        this.desarrolladoraRepository = desarrolladoraRepository;
    }

    // Listar todas las desarrolladoras
    public List<Desarrolladora> listarTodas() {
        return desarrolladoraRepository.findAll();
    }

    // Crear nueva desarrolladora
    public Desarrolladora crear(Desarrolladora desarrolladora) {
        return desarrolladoraRepository.save(desarrolladora);
    }

    // Buscar por id
    public Desarrolladora buscarPorId(Long id) {
        return desarrolladoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Desarrolladora no encontrada con ID: " + id));
    }
}
