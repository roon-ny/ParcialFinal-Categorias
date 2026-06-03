package pa.categoria.Client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pa.categoria.Client.Dto.CarreraDto;

/**
 * componente que hace llamadas REST al servicio de carreras, se usa para
 * consultar las carreras de una categoria o para quitarle la categoria
 * a una carrera especifica
 */
@Component
public class CarreraClient {

    /**
     * RestClient inyectado para comunicarse con el servicio de carreras
     */
    @Autowired
    @Qualifier("carreraRestClient")
    private RestClient carreraRestClient;

    /**
     * consulta todas las carreras que pertenecen a una categoria
     *
     * @param categoriaId id de la categoria
     * @return lista de dto de carreras de esa categoria
     */
    public List<CarreraDto> consultarCarrerasPorCategoria(String categoriaId) {
        return carreraRestClient.get()
                .uri("/carreras/consultar/carreras/categoria/{identificacionCategoria}", categoriaId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CarreraDto>>() {
                });
    }

    /**
     * notifica al servicio de carreras que debe quitarle la categoria a una carrera
     *
     * @param identificacionCarrera id de la carrera a modificar
     */
    public void eliminarCategoriaDeCarrera(String identificacionCarrera) {
        carreraRestClient.delete()
                .uri("/carreras/eliminar/categoria/{identificacionCarrera}", identificacionCarrera)
                .retrieve()
                .toBodilessEntity();
    }
}