package com.api.veroneze.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todos os endpoints
                .allowedOrigins("*") // Permite todas as origens
                .allowedMethods("*") // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(false); // Não permite cookies compartilhados por segurança
    }
}
