package config;  // Usa tu paquete base

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Indica que esta clase es de configuración
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permite CORS en todos los endpoints
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes desde Angular (localhost:4200)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todos los encabezados
                .allowCredentials(true);  // Permite credenciales (si es necesario)
    }
}