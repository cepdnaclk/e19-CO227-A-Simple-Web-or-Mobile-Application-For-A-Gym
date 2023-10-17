package com.e19co227.gymhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Configuration to enable Cross-Origin Resource Sharing (CORS).
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Allow cross-origin requests from all origins, and specify allowed HTTP methods.
        registry.addMapping("/**")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedOrigins("*");
    }
}