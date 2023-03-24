package org.indexmonitor.client.application.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class ClientAppSecurityConfig {

    @Bean
    public SecurityFilterChain clientAppSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }
}
