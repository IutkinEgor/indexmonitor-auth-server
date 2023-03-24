package org.indexmonitor.client.adapter.in.api.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
class ClientApiSecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtDecoder jwtDecoder;

//    @Bean
//    SecurityFilterChain clientApiSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.cors().configurationSource(corsConfigurationSource);
////        http
////                .securityMatcher("/api/client/**", "api/scope/**")
////                .authorizeHttpRequests()
////                .requestMatchers("/api/**", "api/scope/**").authenticated()
////                .and()
////                .oauth2ResourceServer((oauth2ResourceServer) -> {
////                    oauth2ResourceServer.jwt((jwt) -> jwt.decoder(jwtDecoder));
////                });
//////                .exceptionHandling((exceptions) -> {
//////                            System.out.println("Exception handler: " + exceptions);
//////                            exceptions.authenticationEntryPoint(
//////                                    new AppAuthenticationEntryPoint());
//////                        }
//////                );
//        return http.build();
//    }

    @Bean
    SecurityFilterChain clientApiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.cors().configurationSource(corsConfigurationSource)
//                .and()
//                .securityMatcher("/api/client/**")
//                .authorizeHttpRequests()
//                .requestMatchers("/api/client/**").hasRole("CLIENT-MANAGER")
//                .and()
//                .oauth2ResourceServer((oauth2ResourceServer) -> {
//                    oauth2ResourceServer.jwt((jwt) -> jwt.decoder(jwtDecoder));
//                })
//                .exceptionHandling((exceptions) -> {
//                            System.out.println("Exception handler: " + exceptions);
////                            exceptions.authenticationEntryPoint(
////                                    new AppAuthenticationEntryPoint());
//                        }
//                );
        return http.build();
    }
}
