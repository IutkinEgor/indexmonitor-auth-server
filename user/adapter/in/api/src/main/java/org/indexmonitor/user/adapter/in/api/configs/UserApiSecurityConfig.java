package org.indexmonitor.user.adapter.in.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class UserApiSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //http.formLogin().loginPage("/login");
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("api/user/**")
//                .authenticated();
        return http.build();
    }
}
