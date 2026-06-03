package pa.categoria.Service;

import java.util.List;
import pa.categoria.Client.Dto.CarreraDto;
import pa.categoria.Modelo.CategoriaCarrera;
import pa.categoria.Modelo.CategoriaResponse;

/**
 * define los metodos del servicio de categorias: crear, actualizar,
 * consultar, eliminar, y gestionar carreras asociadas
 */
public interface ICategoriaService {

    /**
     * crea una nueva categoria, valida campos obligatorios y la guarda
     *
     * @param categoria objeto con los datos a registrar
     * @return response con los datos guardados
     */
    CategoriaResponse crear(CategoriaCarrera categoria);

    /**
     * actualiza solo la descripcion de una categoria, valida que no este vacia
     *
     * @param id identificador de la categoria
     * @param descripcion nueva descripcion
     * @return response con la descripcion actualizada
     */
    CategoriaResponse actualizarDescripcion(String id, String descripcion);

    /**
     * actualiza solo la recomendacion de una categoria
     *
     * @param id identificador de la categoria
     * @param recomendacion nueva recomendacion
     * @return response con la recomendacion actualizada
     */
    CategoriaResponse actualizarRecomendacion(String id, String recomendacion);

    /**
     * consulta una categoria por su identificacion publica
     *
     * @param id identificador de la categoria
     * @return response de la categoria encontrada
     */
    CategoriaResponse consultarPorIdentificacion(String id);

    /**
     * devuelve todas las categorias registradas, si no hay devuelve lista vacia
     *
     * @return lista de responses de todas las categorias
     */
    List<CategoriaResponse> consultarTodas();

    /**
     * elimina una categoria por su identificacion
     *
     * @param id identificador de la categoria a eliminar
     */
    void eliminar(String id);

    /**
     * consulta todas las carreras que pertenecen a una categoria,
     * obtiene los datos desde el servicio de carreras
     *
     * @param identificacionCategoria id de la categoria
     * @return lista de dto de carreras de esa categoria
     */
    List<CarreraDto> consultarCarrerasEnCategoria(String identificacionCategoria);

    /**
     * desvincula una carrera de una categoria, notifica al servicio de carreras
     *
     * @param categoriaId id de la categoria
     * @param carreraId id de la carrera a desvincular
     */
    void eliminarCarreraDeCategoria(String categoriaId, String carreraId);
}