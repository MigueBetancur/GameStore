package co.edu.uniquindio.tallergamestore.controller;

import co.edu.uniquindio.tallergamestore.entity.Desarrolladora;
import co.edu.uniquindio.tallergamestore.service.DesarrolladoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladoras")
public class DesarrolladoraController {

    private final DesarrolladoraService desarrolladoraService;

    public DesarrolladoraController(DesarrolladoraService desarrolladoraService) {
        this.desarrolladoraService = desarrolladoraService;
    }

    // GET /api/desarrolladoras — Listar todas las Desarrolladoras existentes
    @GetMapping
    public ResponseEntity<List<Desarrolladora>> listarTodas() {
        return ResponseEntity.ok(desarrolladoraService.listarTodas());
    }

    // POST /api/desarrolladoras — Crear nueva Desarrolladora
    @PostMapping
    public ResponseEntity<Desarrolladora> crear(@RequestBody Desarrolladora desarrolladora) {
        Desarrolladora nueva = desarrolladoraService.crear(desarrolladora);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
