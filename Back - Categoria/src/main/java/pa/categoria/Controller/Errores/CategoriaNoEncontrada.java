package pa.categoria.Controller.Errores;

/**
 * excepcion que se lanza cuando se consulta una categoria y no aparece en la
 * base de datos, extiende RuntimeException para ser unchecked
 */
public class CategoriaNoEncontrada extends RuntimeException {

    /**
     * crea la excepcion con la identificacion de la categoria que se busco
     * y no se encontro, asi queda claro cual fue la que no existe
     *
     * @param identificacionCategoria identificador de la categoria no encontrada
     */
    public CategoriaNoEncontrada(String identificacionCategoria) {
        super("La categoria con el id: " + identificacionCategoria + " no fue encontrada");
    }
}