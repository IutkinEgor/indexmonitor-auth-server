package org.indexmonitor.auth.application.configs;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
class AuthAppCorsConfig {

    @Value("#{'${app.cors.origin.list}'.split(',')}")
    @Nullable
    private List<String> cors_origin;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        if(cors_origin == null || cors_origin.isEmpty() || cors_origin.contains("*")) {
            configuration.setAllowedOriginPatterns(List.of("http://*", "https://*"));
        }else {
            configuration.setAllowedOrigins(cors_origin);
        }
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
