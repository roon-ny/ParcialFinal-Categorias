package pa.categoria.Client.Dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto para deserializar los datos de una carrera que vienen del servicio
 * de carreras, contiene los campos necesarios y solo vive en memoria
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDto {

    /**
     * id de la carrera en el servicio de carreras
     */
    private Long id;

    /**
     * nombre de la carrera
     */
    private String nombre;

    /**
     * ubicacion geografica de la carrera
     */
    private String ubicacion;

    /**
     * fecha de ejecucion de la carrera, solo fecha sin hora
     */
    private LocalDate fechaEjecucion;

    /**
     * nivel de dificultad del 1 al 5
     */
    private Integer nivelDificultad;

    /**
     * descripcion de para quien esta disenada la carrera
     */
    private String paraQuien;

    /**
     * identificador de la categoria asociada a la carrera
     */
    private String identificacionCategoria;

    /**
     * identificador  de la carrera
     */
    private String identificacionCarrera;
}