package pa.categoria.Controller.Mapper;

import org.springframework.stereotype.Component;
import pa.categoria.Modelo.CategoriaCarrera;
import pa.categoria.Modelo.CategoriaResponse;

/**
 * convierte entidades CategoriaCarrera en objetos CategoriaResponse,
 * copia los campos uno por uno sin aplicar logica de negocio
 */
@Component
public class CategoriaMapper {

    /**
     * copia todos los campos de una entidad CategoriaCarrera a un CategoriaResponse
     *
     * @param categoria la entidad a convertir
     * @return response con los mismos datos
     */
    public CategoriaResponse toResponse(CategoriaCarrera categoria) {
        CategoriaResponse cr = new CategoriaResponse();
        cr.setId(categoria.getId());
        cr.setNombre(categoria.getNombre());
        cr.setTipo(categoria.getTipo());
        cr.setDescripcion(categoria.getDescripcion());
        cr.setRecomendacion(categoria.getRecomendacion());
        cr.setIdentificacionCategoria(categoria.getIdentificacionCategoria());
        return cr;
    }
}