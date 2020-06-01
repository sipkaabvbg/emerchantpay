package com.example.merchant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by aivanov.
 */

@Configuration
public class WebMvcConfigurerImp implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST",  "PUT","HEAD", "OPTIONS", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
