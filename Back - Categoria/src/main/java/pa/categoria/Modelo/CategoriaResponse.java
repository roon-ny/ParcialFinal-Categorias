package pa.categoria.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto que contiene los datos de una categoria para ser enviado al cliente,
 * no tiene anotaciones jpa ni logica de negocio, solo transporta datos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    /**
     * id de la categoria generado por la base de datos
     */
    private Long id;

    /**
     * nombre general de la categoria, por ejemplo Categoria por distancia
     */
    private String nombre;

    /**
     * tipo especifico dentro de la categoria, por ejemplo Super Sprint
     */
    private String tipo;

    /**
     * descripcion detallada de las distancias o caracteristicas
     */
    private String descripcion;

    /**
     * recomendacion de para quien esta disenada la categoria
     */
    private String recomendacion;

    /**
     * identificador de la categoria
     */
    private String identificacionCategoria;
}