package pe.edu.upc.mikhuapp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Indica que el cors va a ser aplicado a las rutas con /api/
                .allowedOrigins("http://localhost:4200") // Indica que puerto puede CONSUMIR las apis de este programa
                .allowedMethods(
                        "GET", "POST", "PUT",
                        "DELETE", "PATCH", "OPTIONS" // Metodos permitidos
                )
                .allowedHeaders("*");
    }
}