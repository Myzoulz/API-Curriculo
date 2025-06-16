package com.myzoul.curriculo.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200", // seu frontend local
                        "http://localhost:8080", // swagger local
                        "front-em-producao.com", // front produção
                        "https://api-curriculo-production.up.railway.app/swagger-ui/index.html#/" // swagger produção, se aplicável
                )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}