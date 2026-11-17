package cl.duoc.biblioteca.repository;

import cl.duoc.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** PATRON REPOSITORY: Spring Data genera el acceso a la tabla LIBRO. */
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
