package co.edu.uniquindio.tallergamestore.service;

import co.edu.uniquindio.tallergamestore.entity.Desarrolladora;
import co.edu.uniquindio.tallergamestore.entity.Videojuego;
import co.edu.uniquindio.tallergamestore.exception.ResourceNotFoundException;
import co.edu.uniquindio.tallergamestore.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;
    private final DesarrolladoraService desarrolladoraService;

    public VideojuegoService(VideojuegoRepository videojuegoRepository,
                             DesarrolladoraService desarrolladoraService) {
        this.videojuegoRepository = videojuegoRepository;
        this.desarrolladoraService = desarrolladoraService;
    }

    // Calcula y asigna el IVA
    private void calcularIva(Videojuego v) {
        v.setPrecioConIva(v.getPrecio() * 1.19);
    }

    private void calcularIvaLista(List<Videojuego> lista) {
        lista.forEach(this::calcularIva);
    }

    // Validaciones antes de crear
    private void validar(Videojuego v) {
        if (v.getTitulo() == null || v.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (v.getPrecio() == null || v.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }

    // Listar todos los Videojuegos
    public List<Videojuego> listarTodos() {
        List<Videojuego> lista = videojuegoRepository.findAll();
        calcularIvaLista(lista);
        return lista;
    }

    // Buscar por id
    public Videojuego buscarPorId(Long id) {
        Videojuego v = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Videojuego no encontrado con ID: " + id));
        calcularIva(v);
        return v;
    }

    // Crear videojuego
    public Videojuego crear(Videojuego videojuego) {
        validar(videojuego);

        // Verificar que la desarrolladora exista
        if (videojuego.getDesarrolladora() != null && videojuego.getDesarrolladora().getId() != null) {
            Desarrolladora d = desarrolladoraService.buscarPorId(
                    videojuego.getDesarrolladora().getId());
            videojuego.setDesarrolladora(d);
        }

        Videojuego guardado = videojuegoRepository.save(videojuego);
        calcularIva(guardado);
        return guardado;
    }

    // Actualizar videojuego completo (PUT)
    public Videojuego actualizar(Long id, Videojuego datos) {
        // Primero verificamos que exista
        Videojuego existente = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Videojuego no encontrado con ID: " + id));

        validar(datos);

        // Verificar que la desarrolladora exista
        if (datos.getDesarrolladora() != null && datos.getDesarrolladora().getId() != null) {
            Desarrolladora d = desarrolladoraService.buscarPorId(
                    datos.getDesarrolladora().getId());
            existente.setDesarrolladora(d);
        }

        // Actualizar los campos de Videojuego
        existente.setTitulo(datos.getTitulo());
        existente.setPrecio(datos.getPrecio());
        existente.setCodigoRegistro(datos.getCodigoRegistro());
        existente.setGenero(datos.getGenero());

        Videojuego actualizado = videojuegoRepository.save(existente);
        calcularIva(actualizado);
        return actualizado;
    }

    // Método de eliminar
    public void eliminar(Long id) {
        if (!videojuegoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Videojuego no encontrado con ID: " + id);
        }
        videojuegoRepository.deleteById(id);
    }

    // Buscar por título
    public List<Videojuego> buscarPorTitulo(String titulo) {
        List<Videojuego> lista = videojuegoRepository.findByTituloContainingIgnoreCase(titulo);
        calcularIvaLista(lista);
        return lista;
    }

    // Buscar por rango de precio
    public List<Videojuego> buscarPorRangoDePrecio(Double min, Double max) {
        List<Videojuego> lista = videojuegoRepository.buscarPorRangoDePrecio(min, max);
        calcularIvaLista(lista);
        return lista;
    }

    // Aplicar descuento
    public Videojuego aplicarDescuento(Long id, Double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }

        Videojuego v = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Videojuego no encontrado con ID: " + id));

        double nuevoPrecio = v.getPrecio() * (1 - porcentaje / 100);
        v.setPrecio(nuevoPrecio);

        Videojuego actualizado = videojuegoRepository.save(v);
        calcularIva(actualizado);
        return actualizado;
    }
}
