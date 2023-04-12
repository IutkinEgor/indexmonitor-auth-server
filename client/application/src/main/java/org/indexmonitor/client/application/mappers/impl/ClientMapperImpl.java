package org.indexmonitor.client.application.mappers.impl;

import org.indexmonitor.client.application.mappers.ClientMapper;
import org.indexmonitor.client.application.mappers.ScopeMapper;
import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateRequest;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.enums.AuthenticationMethod;
import org.indexmonitor.common.domain.enums.OAuthGrantType;
import org.indexmonitor.client.domain.valueObjects.ClientSecret;
import org.indexmonitor.client.domain.valueObjects.ClientSettings;
import org.indexmonitor.client.domain.valueObjects.ClientTokenSettings;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class ClientMapperImpl implements ClientMapper {

    private final ScopeMapper scopeMapper;

    @Override
    public Client mapRegisterRequest(ClientRegisterRequest request, BaseId id, ClientSecret secret, Set<Scope> scopeSet) {
        Client client = Client.builder()
                .id(id)
                .clientId(request.getClientId())
                .issuedAt(request.getClientIdIssuedAt())
                .secret(secret)
                .name(request.getName())
                .description(request.getDescription())
                .authenticationMethods(request.getAuthenticationMethods().stream().map(method -> AuthenticationMethod.valueOf(method)).collect(Collectors.toSet()))
                .authorizationGrantTypes(request.getAuthorizationGrantTypes().stream().map(type -> OAuthGrantType.valueOf(type)).collect(Collectors.toSet()))
                .origin(URI.create(request.getOrigin()))
                .redirectUris(request.getRedirectUris().stream().map(url -> URI.create(url)).collect(Collectors.toSet()))
                .scopes(scopeSet)
                .clientSettings(ClientSettings.withDefault())
                .tokenSettings(ClientTokenSettings.withDefault())
                .build();
        return client;
    }

    @Override
    public Client mapUpdateRequest(Client client, ClientUpdateRequest request) {
        Client updateClient = Client.builder()
                .id(new BaseId<>(request.getId()))
                .clientId(request.getClientId())
                .name(request.getName())
                .description(request.getDescription())
                .authenticationMethods(request.getAuthenticationMethods().stream().map(method -> AuthenticationMethod.valueOf(method)).collect(Collectors.toSet()))
                .authorizationGrantTypes(request.getAuthorizationGrantTypes().stream().map(type -> OAuthGrantType.valueOf(type)).collect(Collectors.toSet()))
                .origin(URI.create(request.getOrigin()))
                .redirectUris(request.getRedirectUris().stream().map(url -> URI.create(url)).collect(Collectors.toSet()))
                .clientSettings(new ClientSettings(request.getRequireProofKey(), request.getRequireAuthorizationConsent()))
                //Not updated
                .issuedAt(client.getIssuedAt())
                .secret(client.getSecret() == null ? null : client.getSecret())
                .scopes(client.getScopes() == null ? Collections.emptySet() : client.getScopes())
                .tokenSettings(client.getTokenSettings())
                .build();
        return updateClient;
    }

}


