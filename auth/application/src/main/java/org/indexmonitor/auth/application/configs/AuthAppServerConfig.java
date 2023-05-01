package org.indexmonitor.auth.application.configs;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
class AuthAppServerConfig {
    @Value("${app.issuer_url}")
    private String issuer_url;
    @Value("${app.cors.origin.enable}")
    private Boolean cors_enable;
    @Value("${app.security.jwt.keyID}")
    private String keyID;
    @Value("${app.security.jwt.publicKey}")
    private String publicKey;
    @Value("${app.security.jwt.privateKey}")
    private String privateKey;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    @Order(1)
    public SecurityFilterChain appAuthServerSecurityFilterChain(HttpSecurity http) throws Exception {
        if(cors_enable){
            http.cors().configurationSource(corsConfigurationSource);
        } else {
            http.cors().disable();
        }
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
        http.oauth2ResourceServer((oauth2ResourceServer) -> {
                    oauth2ResourceServer.jwt((jwt) -> jwt.decoder(jwtDecoder()));
                })
                .exceptionHandling((exceptions) -> {
                            exceptions.authenticationEntryPoint(
                                    new LoginUrlAuthenticationEntryPoint(issuer_url + "/login"));
                        }
                );
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer(issuer_url)
                .build();
    }


    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        try{
        var privateKeyContent = privateKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        var publicKeyContent = publicKey.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");;
            KeyFactory kf = KeyFactory.getInstance("RSA");

            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);

            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);

            RSAKey rsaKey = new RSAKey.Builder(pubKey)
                    .privateKey((RSAPrivateKey) privKey)
                    .keyID(this.keyID)
                    .build();
            JWKSet jwkSet = new JWKSet(rsaKey);
            return new ImmutableJWKSet<>(jwkSet);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource());
    }
}