package pa.categoria.Controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pa.categoria.Client.Dto.CarreraDto;
import pa.categoria.Modelo.CategoriaCarrera;
import pa.categoria.Modelo.CategoriaResponse;
import pa.categoria.Service.ICategoriaService;

/**
 * controlador REST que expone los endpoints para gestionar categorias,
 * no tiene logica de negocio, solo recibe peticiones, las pasa al servicio
 * y arma las respuestas http
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaRestController {

    /**
     * servicio de categorias inyectado
     */
    @Autowired
    private ICategoriaService service;

    /**
     * crea una nueva categoria, recibe el objeto en el cuerpo del json
     *
     * @param categoria objeto con los datos de la categoria
     * @return response con los datos guardados y codigo 201
     */
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaCarrera categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(categoria));
    }

    /**
     * actualiza solo la descripcion de una categoria, el id va en el path
     * y la nueva descripcion como query param
     *
     * @param id identificador de la categoria
     * @param descripcion nueva descripcion
     * @return response con la descripcion actualizada
     */
    @RequestMapping(value = "/actualizar/descripcion/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<CategoriaResponse> actualizarDescripcion(
            @PathVariable String id,
            @RequestParam String descripcion) {
        return ResponseEntity.ok(service.actualizarDescripcion(id, descripcion));
    }

    /**
     * actualiza solo la recomendacion de una categoria, igual que el anterior
     *
     * @param id identificador de la categoria
     * @param recomendacion nueva recomendacion
     * @return response con la recomendacion actualizada
     */
    @RequestMapping(value = "/actualizar/recomendacion/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<CategoriaResponse> actualizarRecomendacion(
            @PathVariable String id,
            @RequestParam String recomendacion) {
        return ResponseEntity.ok(service.actualizarRecomendacion(id, recomendacion));
    }

    /**
     * consulta una categoria por su identificador
     *
     * @param id de la categoria
     * @return response de la categoria encontrada
     */
    @RequestMapping(value = "/consultar/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoriaResponse> consultarPorIdentificacion(@PathVariable String id) {
        return ResponseEntity.ok(service.consultarPorIdentificacion(id));
    }

    /**
     * devuelve todas las categorias registradas, si no hay devuelve lista vacia
     *
     * @return lista de responses de todas las categorias
     */
    @RequestMapping(value = "/consultar/todas", method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaResponse>> consultarTodas() {
        return ResponseEntity.ok(service.consultarTodas());
    }

    /**
     * consulta todas las carreras que pertenecen a una categoria,
     * el servicio las obtiene desde el proyecto de carreras
     *
     * @param identificacionCategoria id de la categoria
     * @return lista de dto de carreras de esa categoria
     */
    @RequestMapping(value = "/{identificacionCategoria}/carreras", method = RequestMethod.GET)
    public ResponseEntity<List<CarreraDto>> consultarCarrerasEnCategoria(
            @PathVariable String identificacionCategoria) {
        return ResponseEntity.ok(service.consultarCarrerasEnCategoria(identificacionCategoria));
    }

    /**
     * elimina una categoria por su identificador
     *
     * @param id de la categoria a borrar
     * @return mapa con mensaje de confirmacion
     */
    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of(
                "mensaje", "Categoria eliminada correctamente",
                "status", 200
        ));
    }

    /**
     * desvincula una carrera de una categoria, notifica al proyecto de carreras
     *
     * @param identificacionCategoria id de la categoria
     * @param identificacionCarrera id de la carrera a desvincular
     * @return mapa con mensaje de confirmacion
     */
    @RequestMapping(value = "/{identificacionCategoria}/carrera/{identificacionCarrera}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> eliminarCarreraDeCategoria(
            @PathVariable String identificacionCategoria,
            @PathVariable String identificacionCarrera) {
        service.eliminarCarreraDeCategoria(identificacionCategoria, identificacionCarrera);
        return ResponseEntity.ok(Map.of(
                "mensaje", "Carrera desvinculada de la categoria correctamente",
                "status", 200
        ));
    }
}