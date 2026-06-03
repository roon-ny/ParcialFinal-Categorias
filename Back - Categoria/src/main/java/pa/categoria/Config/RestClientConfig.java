package pa.categoria.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * clase de configuracion que crea el bean de RestClient para comunicarse con
 * el Back Carreras, la URL base se lee del application.properties con @Value
 * para que si cambia el puerto no haya que tocar el codigo, solo las propiedades
 */
@Configuration
public class RestClientConfig {

    /**
     * URL base del Back Carreras leida del application.properties, por defecto
     * es http://localhost:9001
     */
    @Value("${carreras.api.url}")
    private String carrerasUrl;

    /**
     * crea el bean de RestClient configurado para llamar al Back Carreras, se
     * inyecta en CarreraClient por el nombre carreraRestClient
     *
     * @return RestClient con la URL base del Back Carreras
     */
    @Bean
    public RestClient carreraRestClient() {
        return RestClient.builder()
                .baseUrl(carrerasUrl)
                .build();
    }
}
