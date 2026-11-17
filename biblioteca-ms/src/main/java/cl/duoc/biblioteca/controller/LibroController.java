package cl.duoc.biblioteca.controller;

import cl.duoc.biblioteca.dto.LibroRequest;
import cl.duoc.biblioteca.dto.LibroResponse;
import cl.duoc.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GET    /api/libros        obtiene todos los libros
 * GET    /api/libros/{id}   obtiene un libro por su ID
 * POST   /api/libros        agrega un libro nuevo
 * PUT    /api/libros/{id}   actualiza un libro existente
 * DELETE /api/libros/{id}   elimina un libro por su ID
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<LibroResponse> listar() {
        return libroService.listar();
    }

    @GetMapping("/{id}")
    public LibroResponse obtener(@PathVariable Long id) {
        return libroService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<LibroResponse> crear(@Valid @RequestBody LibroRequest datos) {
        LibroResponse creado = libroService.crear(datos);
        return ResponseEntity.created(URI.create("/api/libros/" + creado.id())).body(creado);
    }

    @PutMapping("/{id}")
    public LibroResponse actualizar(@PathVariable Long id, @Valid @RequestBody LibroRequest datos) {
        return libroService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
