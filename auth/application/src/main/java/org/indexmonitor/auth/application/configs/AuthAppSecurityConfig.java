package org.indexmonitor.auth.application.configs;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.application.models.UserAccountDetails;
import org.indexmonitor.auth.application.services.AppUserDetailsService;
import org.indexmonitor.auth.application.services.OidcUserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@AllArgsConstructor
class AuthAppSecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;
    private final AppUserDetailsService userDetailsService;
    private final JwtDecoder jwtDecoder;

    @Bean
    @Order(2)
    SecurityFilterChain apiFilter(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource)
                .and()
                .securityMatcher("/api/**")
                .authorizeHttpRequests()
                .requestMatchers("/api/**").authenticated()
                .and()
                .oauth2ResourceServer((oauth2ResourceServer) -> {
                    oauth2ResourceServer.jwt((jwt) -> jwt.decoder(jwtDecoder));
                })
                .exceptionHandling((exceptions) -> {
                            System.out.println("Exception handler: " + exceptions);
                        }
                );
        return http.build();
    }
    @Bean
    @Order(3)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource)
                .and()
                .securityMatcher("/oauth2/**", "/login/**", "/register/**", "/userinfo", "/confirm-email/**")
                .authorizeHttpRequests()
                .requestMatchers("/oauth2/**", "/login/**", "/register/**", "/userinfo", "/confirm-email/**")
                .permitAll()
                .and()
                .httpBasic().and()
                .formLogin().loginPage("/login").usernameParameter("email");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(delegatingPasswordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder delegatingPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> auth2TokenCustomizer(OidcUserInfoService oidcUserInfoService){
        return context -> {
            if(OAuth2TokenType.ACCESS_TOKEN.getValue().equals(context.getTokenType().getValue())){
                context.getClaims().subject(((UserAccountDetails) context.getPrincipal().getPrincipal()).getUserId());
                context.getClaims().claim("roles", ((UserAccountDetails) context.getPrincipal().getPrincipal()).getRolesName());
                context.getClaims().claim("authorities", ((UserAccountDetails) context.getPrincipal().getPrincipal()).getAuthoritiesName());
            }
            if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
                OidcUserInfo userInfo = oidcUserInfoService.buildUserInfo(
                        ((UserAccountDetails) context.getPrincipal().getPrincipal()),
                        context.getAuthorizedScopes());
                context.getClaims().claims(claims ->
                        claims.putAll(userInfo.getClaims()));
            }
        };
    }
}
