package cl.duoc.biblioteca.service;

import cl.duoc.biblioteca.comun.patron.CrudServiceBase;
import cl.duoc.biblioteca.dto.LibroRequest;
import cl.duoc.biblioteca.dto.LibroResponse;
import cl.duoc.biblioteca.model.Libro;
import cl.duoc.biblioteca.repository.LibroRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio de libros. Hereda el flujo CRUD del patron TEMPLATE METHOD
 * (CrudServiceBase, que viene del arquetipo) y solo implementa los pasos
 * propios de Libro. Para crear la entidad usa el patron BUILDER.
 */
@Service
public class LibroService extends CrudServiceBase<Libro, Long, LibroRequest, LibroResponse> {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    protected JpaRepository<Libro, Long> repositorio() {
        return libroRepository;
    }

    @Override
    protected Libro crearEntidad(LibroRequest datos) {
        return Libro.builder()
                .titulo(datos.titulo())
                .autor(datos.autor())
                .anioPublicacion(datos.anioPublicacion())
                .genero(datos.genero())
                .build();
    }

    @Override
    protected void actualizarEntidad(Libro libro, LibroRequest datos) {
        libro.setTitulo(datos.titulo());
        libro.setAutor(datos.autor());
        libro.setAnioPublicacion(datos.anioPublicacion());
        libro.setGenero(datos.genero());
    }

    @Override
    protected LibroResponse aRespuesta(Libro libro) {
        return new LibroResponse(libro.getId(), libro.getTitulo(), libro.getAutor(),
                libro.getAnioPublicacion(), libro.getGenero());
    }

    @Override
    protected String nombreRecurso() {
        return "el libro";
    }
}
