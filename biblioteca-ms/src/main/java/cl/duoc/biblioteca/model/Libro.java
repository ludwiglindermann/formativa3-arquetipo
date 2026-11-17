package cl.duoc.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad Libro, mapeada a la tabla LIBRO de Oracle.
 *
 * PATRON DE DISENO: BUILDER (creacional).
 * Los libros se construyen paso a paso con Libro.builder()...build(),
 * lo que evita constructores con muchos parametros del mismo tipo
 * (titulo, autor y genero son todos String) y deja el codigo mas legible.
 */
@Entity
@Table(name = "LIBRO")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;

    @Column(name = "AUTOR", nullable = false, length = 150)
    private String autor;

    @Column(name = "ANIO_PUBLICACION", nullable = false)
    private Integer anioPublicacion;

    @Column(name = "GENERO", nullable = false, length = 80)
    private String genero;

    /** Constructor requerido por JPA. */
    protected Libro() {
    }

    private Libro(Builder b) {
        this.titulo = b.titulo;
        this.autor = b.autor;
        this.anioPublicacion = b.anioPublicacion;
        this.genero = b.genero;
    }

    public static Builder builder() {
        return new Builder();
    }

    /** Builder de Libro. */
    public static final class Builder {
        private String titulo;
        private String autor;
        private Integer anioPublicacion;
        private String genero;

        private Builder() {
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder autor(String autor) {
            this.autor = autor;
            return this;
        }

        public Builder anioPublicacion(Integer anioPublicacion) {
            this.anioPublicacion = anioPublicacion;
            return this;
        }

        public Builder genero(String genero) {
            this.genero = genero;
            return this;
        }

        public Libro build() {
            return new Libro(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
