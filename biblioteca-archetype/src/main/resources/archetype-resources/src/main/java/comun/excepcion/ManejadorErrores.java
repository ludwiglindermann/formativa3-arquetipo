package ${package}.comun.excepcion;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Manejo global de errores: todas las respuestas de error usan el mismo JSON. */
@RestControllerAdvice
public class ManejadorErrores {

    private static final Logger log = LoggerFactory.getLogger(ManejadorErrores.class);

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> noEncontrado(RecursoNoEncontradoException ex) {
        return respuesta(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> datosInvalidos(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> campos.put(e.getField(), e.getDefaultMessage()));
        return respuesta(HttpStatus.BAD_REQUEST, "Los datos enviados no son validos", campos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> jsonInvalido(HttpMessageNotReadableException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, "El cuerpo de la peticion no es un JSON valido", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> errorInesperado(Exception ex) {
        log.error("Error inesperado", ex);
        return respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrio un error inesperado en el servidor", null);
    }

    private ResponseEntity<Map<String, Object>> respuesta(HttpStatus estado, String mensaje,
                                                          Map<String, String> campos) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("fecha", LocalDateTime.now().toString());
        cuerpo.put("estado", estado.value());
        cuerpo.put("error", mensaje);
        if (campos != null) {
            cuerpo.put("campos", campos);
        }
        return ResponseEntity.status(estado).body(cuerpo);
    }
}
