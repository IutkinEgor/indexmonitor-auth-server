package org.indexmonitor.auth.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "authorizations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationEntity {
    @Id
    private String id;
    private String registeredClientId;
    private String principalName;
    @Enumerated
    private AuthorizationGrantType authorizationGrantType;
    @ElementCollection
    private Set<String> authorizedScopes;
    @ElementCollection
    private Map<Class<? extends OAuth2Token>, OAuth2Authorization.Token<?>> tokens;
    @ElementCollection
    private Map<String, Object> attributes;

    public void addToken(Class<? extends OAuth2Token> key, OAuth2Authorization.Token<?> value){
        this.tokens.put(key, value);
    }
}
