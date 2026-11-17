# Formativa 3 – Implementando lineamientos empresariales (DSY2205, Semana 4)

Arquetipo Maven propio y microservicio de biblioteca generado a partir de él,
conectado a Oracle Autonomous Database (Oracle Cloud).

## Contenido

| Carpeta | Qué es |
|---|---|
| `biblioteca-archetype/` | **Arquetipo Maven** (`cl.duoc.arquetipos:microservicio-crud-archetype:1.0.0`): plantilla para crear microservicios CRUD con Spring Boot + Oracle |
| `biblioteca-ms/` | **Microservicio de libros** generado con el arquetipo |
| `scripts/` | Scripts SQL de Oracle (usuario `BIBLIOTECA_MS` y tabla `LIBRO`) |
| `postman/` | Colección de pruebas del CRUD |

## Patrones de diseño

| Patrón | Tipo | Dónde |
|---|---|---|
| **Template Method** | Comportamiento | `CrudServiceBase` (incluido en el arquetipo): define el flujo CRUD una vez; cada servicio implementa solo sus pasos (`LibroService`) |
| **Builder** | Creacional | `Libro.builder()...build()` para construir la entidad |
| Repository | Arquitectónico | `LibroRepository` (Spring Data JPA) |
| DTO | Arquitectónico | `LibroRequest` / `LibroResponse` |

## Endpoints (http://localhost:8080)

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/libros` | Obtener todos los libros |
| GET | `/api/libros/{id}` | Obtener un libro por su ID |
| POST | `/api/libros` | Agregar un libro |
| PUT | `/api/libros/{id}` | Actualizar un libro |
| DELETE | `/api/libros/{id}` | Eliminar un libro |

## Usar el arquetipo

```bash
# 1. Instalar el arquetipo en el repositorio Maven local
cd biblioteca-archetype
mvn clean install

# 2. Generar un microservicio nuevo con el arquetipo
cd ..
mvn archetype:generate -DarchetypeGroupId=cl.duoc.arquetipos -DarchetypeArtifactId=microservicio-crud-archetype -DarchetypeVersion=1.0.0 -DgroupId=cl.duoc -DartifactId=biblioteca-ms -Dversion=1.0.0 -Dpackage=cl.duoc.biblioteca -DdbUser=BIBLIOTECA_MS -DserverPort=8080 -DinteractiveMode=false
```

## Estrategia de branching (Git Flow simplificado)

| Rama | Uso |
|---|---|
| `main` | Versiones estables, marcadas con tags (`v1.0.0`) |
| `develop` | Integración de funcionalidades terminadas |
| `feature/arquetipo-maven` | Desarrollo del arquetipo |
| `feature/microservicio-libros` | Desarrollo del microservicio, scripts y pruebas |

Cada `feature/*` nace de `develop` y vuelve a ella con un merge `--no-ff`.
Cuando `develop` está estable, se integra a `main` y se crea un tag de versión.
