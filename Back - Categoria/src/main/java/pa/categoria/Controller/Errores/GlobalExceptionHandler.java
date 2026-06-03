package pa.categoria.Controller.Errores;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * manejador global de excepciones para el controlador REST, concentra el
 * manejo de errores en un solo lugar para que el controlador no tenga try catch,
 * captura las excepciones personalizadas y las convierte en respuestas HTTP con
 * un JSON estandar que siempre tiene timestamp, status, error y mensaje
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * atrapa CamposFaltantes y responde con 400 Bad Request, el cuerpo lleva
     * el mensaje que dice que campo falto
     *
     * @param ex excepcion CamposFaltantes con el mensaje del campo faltante
     * @return ResponseEntity con el mapa de error estandar y codigo 400
     */
    @ExceptionHandler(CamposFaltantes.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(CamposFaltantes ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse("BAD_REQUEST", ex.getMessage(), 400));
    }

    /**
     * atrapa CategoriaNoEncontrada y responde con 404 Not Found, el cuerpo
     * lleva el mensaje que dice cual fue el id que no existe
     *
     * @param ex excepcion CategoriaNoEncontrada con el id buscado
     * @return ResponseEntity con el mapa de error estandar y codigo 404
     */
    @ExceptionHandler(CategoriaNoEncontrada.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(CategoriaNoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse("NOT_FOUND", ex.getMessage(), 404));
    }

    /**
     * arma el cuerpo de la respuesta de error con el formato estandar, todos
     * los handlers lo usan para no repetir codigo, devuelve timestamp, status,
     * error y mensaje
     *
     * @param error tipo de error como BAD_REQUEST o NOT_FOUND
     * @param mensaje descripcion del error que viene de la excepcion
     * @param status codigo http numerico 400 404 etc
     * @return Map con los cuatro campos del formato estandar de error
     */
    private Map<String, Object> buildResponse(String error, String mensaje, int status) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status,
                "error", error,
                "mensaje", mensaje
        );
    }
}
