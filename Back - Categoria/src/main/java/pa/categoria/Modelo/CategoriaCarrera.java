package pa.categoria.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entidad que representa una categoria de carrera de triatlon, se persiste en
 * la tabla Categorias
 */
@Entity
@Data
@Table(name = "Categorias")
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCarrera {

    /**
     * identificador unico generado por la base de datos automaticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * nombre general de la categoria
     */
    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * tipo especifico dentro de la categoria, como Super Sprint u Olimpica,
     * no puede estar vacio
     */
    @NotBlank(message = "El tipo no puede estar vacio")
    @Column(name = "tipo", nullable = false)
    private String tipo;

    /**
     * descripcion detallada de las distancias o caracteristicas de la categoria,
     * es el campo que se actualiza con actualizarDescripcion
     */
    @NotBlank(message = "La descripcion no puede estar vacia")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    /**
     * recomendacion de para quien esta disenada la categoria,
     * se actualiza con actualizarRecomendacion
     */
    @NotBlank(message = "La recomendacion no puede estar vacia")
    @Column(name = "recomendacion", nullable = false)
    private String recomendacion;

    /**
     * identificadorde la categoria, se usa como referencia
     * desde otros proyectos, no puede repetirse en la base de datos
     */
    @NotBlank(message = "La identificacion no puede estar vacia")
    @Column(name = "identificacion_categoria", nullable = false, unique = true)
    private String identificacionCategoria;
}