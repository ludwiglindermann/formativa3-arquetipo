package cl.duoc.biblioteca.dto;

/** Datos de salida de un libro. */
public record LibroResponse(Long id, String titulo, String autor, Integer anioPublicacion, String genero) {
}
