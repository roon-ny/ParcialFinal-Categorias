package pa.categoria.Controller.Errores;

/**
 * excepcion que se lanza cuando al crear o modificar una categoria no se mandan
 * todos los campos obligatorios, extiende RuntimeException para que sea unchecked
 * y no haya que declararla en cada metodo, el GlobalExceptionHandler la atrapa
 * y responde con un 400 Bad Request
 */
public class CamposFaltantes extends RuntimeException {

    /**
     * crea la excepcion con un mensaje que dice cual campo obligatorio no se
     * envio en la peticion
     *
     * @param mensaje el nombre del campo que no se mando y era obligatorio
     */
    public CamposFaltantes(String mensaje) {
        super("Faltan campos obligatorios: " + mensaje);
    }
}
