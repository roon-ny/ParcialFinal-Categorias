package pa.categoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * clase principal que arranca el sistema de categorias de triatlon, es el punto
 * de entrada de la aplicacion, cuando se ejecuta levanta el contexto de Spring
 * y escanea automaticamente todos los componentes del paquete pa.categoria y
 * sus subpaquetes sin que uno tenga que configurar nada a mano
 */
@SpringBootApplication
public class CategoriaApplication {

    /**
     * lanza la aplicacion, Spring se encarga de levantar el servidor y dejar
     * todo corriendo con la clase principal y los argumentos de linea de comandos
     *
     * @param args argumentos de linea de comandos, por lo general no se usan
     */
    public static void main(String[] args) {
        SpringApplication.run(CategoriaApplication.class, args);
    }

    /**
     * configura el CORS para que el frontend y el backend se puedan comunicar
     * aunque esten en puertos distintos, sin esto el navegador bloquea las
     * peticiones y la aplicacion no funciona desde el front
     *
     * @return WebMvcConfigurer con las reglas de CORS para toda la aplicacion
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * define las reglas de acceso para todos los endpoints con el
             * addMapping de /**, acepta el frontend del puerto 8383 de Netbeans,
             * permite todos los metodos y headers con el asterisco, y el maxAge
             * de 3600 cachea la respuesta preflight por una hora
             *
             * @param registry objeto donde se registran las reglas CORS
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8383")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}
