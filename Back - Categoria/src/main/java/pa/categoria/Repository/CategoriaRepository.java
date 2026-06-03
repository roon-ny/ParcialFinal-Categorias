package pa.categoria.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pa.categoria.Modelo.CategoriaCarrera;

/**
 * repositorio para acceder a los datos de la entidad CategoriaCarrera,
 * extiende JpaRepository con la entidad y el tipo de id Long,
 * incluye metodos adicionales para buscar y eliminar por identificacion
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaCarrera, Long> {

    /**
     * busca una categoria por su identificacion publica
     *
     * @param identificacionCategoria id publico de la categoria
     * @return optional con la categoria si existe, vacio si no
     */
    Optional<CategoriaCarrera> findByIdentificacionCategoria(String identificacionCategoria);

    /**
     * elimina una categoria por su identificacion publica
     *
     * @param identificacionCategoria id publico de la categoria a borrar
     */
    void deleteByIdentificacionCategoria(String identificacionCategoria);
}