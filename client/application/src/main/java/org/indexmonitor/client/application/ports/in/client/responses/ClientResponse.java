package org.indexmonitor.client.application.ports.in.client.responses;

import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private String id;
    private String clientId;
    private Long createdAt;
    private String name;
    private String description;
    private Set<String> authenticationMethods;
    private Set<String> authorizationGrantTypes;
    private String origin;
    private Set<String> redirectUris;
    private Set<ScopePageResponse> scopes;
    private Long authorizationCodeTimeToLive;
    private Long accessTokenTimeToLive;
    private Long refreshTokenTimeToLive;
    private boolean reuseRefreshTokens;
    private String tokenSignatureAlgorithm;
    private boolean requireProofKey;
    private boolean requireAuthorizationConsent;
}
