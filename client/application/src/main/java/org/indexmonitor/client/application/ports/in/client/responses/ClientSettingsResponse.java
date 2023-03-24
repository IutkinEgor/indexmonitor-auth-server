package org.indexmonitor.client.application.ports.in.client.responses;

import org.indexmonitor.client.domain.aggregates.Client;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ClientSettingsResponse {
    private String id;
    private String clientId;
    private Long createdAt;
    private String name;
    private String description;
    private Set<String> authenticationMethods;
    private Set<String> authorizationGrantTypes;
    private String origin;
    private Set<String> redirectUris;
    private boolean requireProofKey;
    private boolean requireAuthorizationConsent;

    public static ClientSettingsResponse map(Client client){
        return ClientSettingsResponse.builder()
                .id(client.getId().getValue().toString())
                .clientId(client.getClientId())
                .createdAt(client.getIssuedAt().toEpochMilli())
                .name(client.getName())
                .description(client.getDescription())
                .authenticationMethods(client.getAuthenticationMethods().stream().map( method -> method.toString()).collect(Collectors.toSet()))
                .authorizationGrantTypes(client.getAuthorizationGrantTypes().stream().map( type -> type.toString()).collect(Collectors.toSet()))
                .origin(client.getOrigin().toString())
                .redirectUris(client.getRedirectUris().stream().map(uri -> uri.toString()).collect(Collectors.toSet()))
                .requireProofKey(client.getClientSettings().isRequireProofKey())
                .requireAuthorizationConsent(client.getClientSettings().isRequireAuthorizationConsent())
                .build();
    }
}
