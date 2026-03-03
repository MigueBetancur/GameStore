package co.edu.uniquindio.tallergamestore.controller;

import co.edu.uniquindio.tallergamestore.entity.Videojuego;
import co.edu.uniquindio.tallergamestore.service.VideojuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    // GET /api/videojuegos — Listar todos los videojuegos
    @GetMapping
    public ResponseEntity<List<Videojuego>> listarTodos() {
        return ResponseEntity.ok(videojuegoService.listarTodos());
    }

    // GET /api/videojuegos/{id} — Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(videojuegoService.buscarPorId(id));
    }

    // POST /api/videojuegos — Crear un Videojuego
    @PostMapping
    public ResponseEntity<Videojuego> crear(@RequestBody Videojuego videojuego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuegoService.crear(videojuego));
    }

    // PUT /api/videojuegos/{id} — Actualizar por completo
    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id,
                                                 @RequestBody Videojuego videojuego) {
        return ResponseEntity.ok(videojuegoService.actualizar(id, videojuego));
    }

    // DELETE /api/videojuegos/{id} — Eliminar un Videojuego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        videojuegoService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // GET /api/videojuegos/buscar?titulo=xyz — Buscar por título
    @GetMapping("/buscar")
    public ResponseEntity<List<Videojuego>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(videojuegoService.buscarPorTitulo(titulo));
    }

    // GET /api/videojuegos/rango-precio?min=10&max=60 — Buscar por rango
    @GetMapping("/rango-precio")
    public ResponseEntity<List<Videojuego>> buscarPorRango(@RequestParam Double min,
                                                           @RequestParam Double max) {
        return ResponseEntity.ok(videojuegoService.buscarPorRangoDePrecio(min, max));
    }

    // PATCH /api/videojuegos/{id}/descuento?porcentaje=10 — Aplicar descuento
    @PatchMapping("/{id}/descuento")
    public ResponseEntity<Videojuego> aplicarDescuento(@PathVariable Long id,
                                                       @RequestParam Double porcentaje) {
        return ResponseEntity.ok(videojuegoService.aplicarDescuento(id, porcentaje));
    }
}
