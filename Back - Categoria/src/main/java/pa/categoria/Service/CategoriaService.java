package pa.categoria.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pa.categoria.Client.CarreraClient;
import pa.categoria.Client.Dto.CarreraDto;
import pa.categoria.Controller.Errores.CamposFaltantes;
import pa.categoria.Controller.Errores.CategoriaNoEncontrada;
import pa.categoria.Controller.Mapper.CategoriaMapper;
import pa.categoria.Modelo.CategoriaCarrera;
import pa.categoria.Modelo.CategoriaResponse;
import pa.categoria.Repository.CategoriaRepository;

/**
 * implementa la logica de negocio del sistema de categorias, valida campos,
 * busca, guarda y coordina llamadas al cliente de carreras, no maneja
 * conversion de datos ni excepciones de http
 */
@Service
public class CategoriaService implements ICategoriaService {

    /**
     * repositorio de categorias para acceder a la base de datos
     */
    @Autowired
    private CategoriaRepository repositorio;

    /**
     * convierte entidades CategoriaCarrera en CategoriaResponse
     */
    @Autowired
    private CategoriaMapper mapper;

    /**
     * cliente para comunicarse con el servicio de carreras
     */
    @Autowired
    private CarreraClient carreraClient;

    /**
     * valida que los campos obligatorios no esten vacios o nulos,
     * si alguno falta lanza CamposFaltantes con el mensaje del campo
     *
     * @param categoria objeto a validar
     */
    private void validarCampos(CategoriaCarrera categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new CamposFaltantes("El nombre no puede estar vacio");
        }
        if (categoria.getTipo() == null || categoria.getTipo().isBlank()) {
            throw new CamposFaltantes("El tipo no puede estar vacio");
        }
        if (categoria.getDescripcion() == null || categoria.getDescripcion().isBlank()) {
            throw new CamposFaltantes("La descripcion no puede estar vacia");
        }
        if (categoria.getRecomendacion() == null || categoria.getRecomendacion().isBlank()) {
            throw new CamposFaltantes("La recomendacion no puede estar vacia");
        }
        if (categoria.getIdentificacionCategoria() == null || categoria.getIdentificacionCategoria().isBlank()) {
            throw new CamposFaltantes("La identificacion no puede estar vacia");
        }
    }

    /**
     * busca una categoria por su identificacion publica, si no existe lanza
     * CategoriaNoEncontrada
     *
     * @param identificacionCategoria id publico de la categoria
     * @return la entidad encontrada
     */
    private CategoriaCarrera buscarPorIdentificacion(String identificacionCategoria) {
        Optional<CategoriaCarrera> optional = repositorio.findByIdentificacionCategoria(identificacionCategoria);
        if (optional.isEmpty()) {
            throw new CategoriaNoEncontrada(identificacionCategoria);
        }
        return optional.get();
    }

    /**
     * crea una nueva categoria, valida campos y la guarda en la base de datos
     *
     * @param categoria objeto con los datos a registrar
     * @return response con los datos guardados
     */
    @Override
    public CategoriaResponse crear(CategoriaCarrera categoria) {
        validarCampos(categoria);
        return mapper.toResponse(repositorio.save(categoria));
    }

    /**
     * actualiza solo la descripcion de una categoria, valida que no este vacia
     *
     * @param id identificador de la categoria
     * @param descripcion nueva descripcion
     * @return response con la descripcion actualizada
     */
    @Override
    public CategoriaResponse actualizarDescripcion(String id, String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new CamposFaltantes("La descripcion no puede estar vacia");
        }
        CategoriaCarrera categoria = buscarPorIdentificacion(id);
        categoria.setDescripcion(descripcion);
        return mapper.toResponse(repositorio.save(categoria));
    }

    /**
     * actualiza solo la recomendacion de una categoria, igual que el anterior
     *
     * @param id identificador de la categoria
     * @param recomendacion nueva recomendacion
     * @return response con la recomendacion actualizada
     */
    @Override
    public CategoriaResponse actualizarRecomendacion(String id, String recomendacion) {
        if (recomendacion == null || recomendacion.isBlank()) {
            throw new CamposFaltantes("La recomendacion no puede estar vacia");
        }
        CategoriaCarrera categoria = buscarPorIdentificacion(id);
        categoria.setRecomendacion(recomendacion);
        return mapper.toResponse(repositorio.save(categoria));
    }

    /**
     * consulta una categoria por su identificacion
     *
     * @param identificacionCategoria id de la categoria
     * @return response de la categoria encontrada
     */
    @Override
    public CategoriaResponse consultarPorIdentificacion(String identificacionCategoria) {
        if (identificacionCategoria == null || identificacionCategoria.isBlank()) {
            throw new CamposFaltantes("La identificacion no puede estar vacia");
        }
        return repositorio.findByIdentificacionCategoria(identificacionCategoria)
                .map(mapper::toResponse)
                .orElseThrow(() -> new CategoriaNoEncontrada(identificacionCategoria));
    }

    /**
     * devuelve todas las categorias registradas, si no hay devuelve lista vacia
     *
     * @return lista de responses de todas las categorias
     */
    @Override
    public List<CategoriaResponse> consultarTodas() {
        return repositorio.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * elimina una categoria por su identificacion publica (no por el id ),
     * por eso usamos @Transactional: necesita una transaccion para que
     * el delete personalizado (por un campo que no es la llave primaria) funcione
     *
     * @param id identificador publico de la categoria a eliminar
     */
    @Override
    @Transactional
    public void eliminar(String id) {
        buscarPorIdentificacion(id);
        repositorio.deleteByIdentificacionCategoria(id);
    }

    /**
     * consulta todas las carreras que pertenecen a una categoria,
     * verifica que la categoria exista y luego llama al cliente de carreras
     *
     * @param categoriaId id de la categoria
     * @return lista de dto de carreras de esa categoria
     */
    @Override
    public List<CarreraDto> consultarCarrerasEnCategoria(String categoriaId) {
        buscarPorIdentificacion(categoriaId);
        return carreraClient.consultarCarrerasPorCategoria(categoriaId);
    }

    /**
     * desvincula una carrera de una categoria, notifica al servicio de carreras
     * para que le quite la referencia, y verifica que la categoria exista antes
     *
     * @param categoriaId id de la categoria
     * @param carreraId id de la carrera a desvincular
     */
    @Override
    @Transactional
    public void eliminarCarreraDeCategoria(String categoriaId, String carreraId) {
        buscarPorIdentificacion(categoriaId);
        carreraClient.eliminarCategoriaDeCarrera(carreraId);
    }
}