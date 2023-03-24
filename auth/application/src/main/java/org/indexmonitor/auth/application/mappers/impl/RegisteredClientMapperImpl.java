package org.indexmonitor.auth.application.mappers.impl;

import org.indexmonitor.auth.application.mappers.AuthenticationMethodMapper;
import org.indexmonitor.auth.application.mappers.GrantTypeMapper;
import org.indexmonitor.auth.application.mappers.RegisteredClientMapper;
import org.indexmonitor.client.domain.aggregates.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class RegisteredClientMapperImpl implements RegisteredClientMapper {

    private final AuthenticationMethodMapper authenticationMethodMapper;
    private final GrantTypeMapper grantTypeMapper;

    @Override
    public RegisteredClient map(Client client) {
        var authMethods = client.getAuthenticationMethods().stream().map(method -> authenticationMethodMapper.map(method)).collect(Collectors.toSet());
        var grantTypes = client.getAuthorizationGrantTypes().stream().map(type -> grantTypeMapper.map(type)).collect(Collectors.toSet());
        var redirectUrl = client.getRedirectUris().stream().map(url -> url.toString()).collect(Collectors.toSet());
        var scopes = client.getScopes().stream().map(scope -> scope.getName()).collect(Collectors.toSet());

        var clientBuilder =  RegisteredClient.withId(client.getId().getValue().toString());
        clientBuilder.clientId(client.getClientId());
        authMethods.forEach(method -> clientBuilder.clientAuthenticationMethod(method));
        grantTypes.forEach(type -> clientBuilder.authorizationGrantType(type));
        redirectUrl.forEach(url -> clientBuilder.redirectUri(url));
        scopes.forEach(scope -> clientBuilder.scope(scope));
        clientBuilder.clientName(client.getName());
        clientBuilder.clientIdIssuedAt(client.getIssuedAt());
        if(client.getSecret() != null && !client.getSecret().getSecreteHash().isEmpty()) {
            clientBuilder.clientSecret(client.getSecret().getSecreteHash());
        }
        clientBuilder.clientSettings(ClientSettings.builder()
                        .requireProofKey(client.getClientSettings().isRequireProofKey())
                        .requireAuthorizationConsent(client.getClientSettings().isRequireAuthorizationConsent())
                        //TODO add other settings. Do refactor
                        .build());
        clientBuilder.tokenSettings(TokenSettings.builder()
                        .authorizationCodeTimeToLive(client.getTokenSettings().getAuthorizationCodeTimeToLive())
                        .accessTokenTimeToLive(client.getTokenSettings().getAccessTokenTimeToLive())
                        .refreshTokenTimeToLive(client.getTokenSettings().getRefreshTokenTimeToLive())
                        .reuseRefreshTokens(client.getTokenSettings().isReuseRefreshTokens())
                        .idTokenSignatureAlgorithm(SignatureAlgorithm.valueOf(client.getTokenSettings().getTokenSignatureAlgorithm()))
                        .build());
        return clientBuilder.build();
    }

    @Override
    public Client map(RegisteredClient client) {
        return null;
    }
}
