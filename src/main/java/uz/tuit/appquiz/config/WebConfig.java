package uz.tuit.appquiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uz.tuit.appquiz.utils.AppConstants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(AppConstants.REACT_URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins(AppConstants.REACT_URL)
                .allowedMethods("GET");
        registry.addMapping("/swagger-ui/**")
                .allowedOrigins(AppConstants.REACT_URL)
                .allowedMethods("GET");
    }
}
