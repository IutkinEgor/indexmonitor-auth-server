package org.indexmonitor.client.domain.aggregates;


import org.indexmonitor.client.domain.models.ClientBuildException;
import org.indexmonitor.client.domain.valueObjects.ClientSecret;
import org.indexmonitor.client.domain.valueObjects.ClientSettings;
import org.indexmonitor.client.domain.valueObjects.ClientTokenSettings;
import org.indexmonitor.common.domain.enums.AuthenticationMethod;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.enums.OAuthGrantType;
import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.net.URI;
import java.time.Instant;
import java.util.*;

public class Client extends AggregateRoot<BaseId> {

    private final String clientId;
    private final Instant issuedAt;
    private final String name;
    private final String description;
    private final ClientSecret secret;
    private final Set<AuthenticationMethod> authenticationMethods;
    private final Set<OAuthGrantType> authorizationGrantTypes;
    private final URI origin;
    private final Set<URI> redirectUris;
    private final Set<Scope> scopes;
    private final ClientSettings clientSettings;
    private final ClientTokenSettings tokenSettings;
    private Client(Builder builder) {
        super(builder.id);
        clientId = builder.clientId;
        issuedAt = builder.issuedAt;
        name = builder.name;
        description = builder.description;
        secret = builder.secret;
        authenticationMethods = builder.authenticationMethods;
        authorizationGrantTypes = builder.authorizationGrantTypes;
        origin = builder.origin;
        redirectUris = builder.redirectUris;
        scopes = builder.scopes;
        clientSettings = builder.clientSettings;
        tokenSettings = builder.tokenSettings;
    }
    public String getClientId() {
        return clientId;
    }
    public Instant getIssuedAt() {
        return issuedAt;
    }
    public ClientSecret getSecret() {
        return secret;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Set<AuthenticationMethod> getAuthenticationMethods() {
        return authenticationMethods;
    }
    public Set<OAuthGrantType> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }
    public URI getOrigin() {
        return origin;
    }
    public Set<URI> getRedirectUris() {
        return redirectUris;
    }
    public Set<Scope> getScopes() {
        return scopes;
    }
    public ClientSettings getClientSettings() {
        return clientSettings;
    }
    public ClientTokenSettings getTokenSettings() {
        return tokenSettings;
    }

    public Client updateTokenSettings(ClientTokenSettings tokenSettings) {
        return Client.builder()
                .id(this.getId())
                .clientId(this.getClientId())
                .issuedAt(this.getIssuedAt())
                .name(this.getName())
                .description(this.getDescription())
                .secret(this.getSecret() == null ? null : this.secret)
                .authenticationMethods(this.getAuthenticationMethods())
                .authorizationGrantTypes(this.getAuthorizationGrantTypes())
                .origin(this.getOrigin())
                .redirectUris(this.getRedirectUris())
                .scopes(this.getScopes())
                .clientSettings(this.getClientSettings())
                .tokenSettings(tokenSettings)
                .build();
    }

    public Client updateScopes(Set<Scope> scopes) {
        return Client.builder()
                .id(this.getId())
                .clientId(this.getClientId())
                .issuedAt(this.getIssuedAt())
                .name(this.getName())
                .description(this.getDescription())
                .secret(this.getSecret() == null ? null : this.secret)
                .authenticationMethods(this.getAuthenticationMethods())
                .authorizationGrantTypes(this.getAuthorizationGrantTypes())
                .origin(this.getOrigin())
                .redirectUris(this.getRedirectUris())
                .scopes(scopes)
                .clientSettings(this.getClientSettings())
                .tokenSettings(this.getTokenSettings())
                .build();
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private BaseId id;
        private String clientId;
        private Instant issuedAt;
        private ClientSecret secret;
        private String name;
        private String description;
        private Set<AuthenticationMethod> authenticationMethods = new HashSet<>();
        private Set<OAuthGrantType> authorizationGrantTypes = new HashSet<>();
        private URI origin;
        private Set<URI> redirectUris = new HashSet<>();
        private Set<Scope> scopes = new HashSet<>();
        private ClientTokenSettings tokenSettings;
        private ClientSettings clientSettings;

        private Builder() {
        }
        public Builder id(BaseId val) {
            this.id = val;
            return this;
        }
        public Builder clientId(String val) {
            this.clientId = val;
            return this;
        }
        public Builder issuedAt(Instant val) {
            this.issuedAt = val;
            return this;
        }
        public Builder secret(ClientSecret val) {
            this.secret = val;
            return this;
        }
        public Builder secret(String hash, EncryptionAlgorithm algorithm, Instant issuedAt) {
            this.secret = new ClientSecret(hash, algorithm);
            return this;
        }
        public Builder name(String val) {
            this.name = val;
            return this;
        }
        public Builder description(String val) {
            this.description = val;
            return this;
        }
        public Builder authenticationMethods(Set<AuthenticationMethod> val) {
            this.authenticationMethods = val;
            return this;
        }

        public Builder authenticationMethods(AuthenticationMethod... val) {
            Collections.addAll(this.authenticationMethods, val);
            return this;
        }

        public Builder authorizationGrantTypes(Set<OAuthGrantType> val) {
            authorizationGrantTypes = val;
            return this;
        }

        public Builder authorizationGrantTypes(OAuthGrantType... val) {
            this.authorizationGrantTypes.addAll(Arrays.asList(val));
            return this;
        }
        public Builder origin(URI val) {
            this.origin = val;
            return this;
        }
        public Builder redirectUris(Set<URI> val) {
            this.redirectUris = val;
            return this;
        }
        public Builder redirectUris(URI... val) {
            Collections.addAll(this.redirectUris, val);
            return this;
        }
        public Builder scopes(Set<Scope> val) {
            this.scopes = val;
            return this;
        }
        public Builder scopes(Scope... val) {
            Collections.addAll(this.scopes, val);
            return this;
        }
        public Builder clientSettings(ClientSettings val) {
            this.clientSettings = val;
            return this;
        }
        public Builder tokenSettings(ClientTokenSettings val) {
            this.tokenSettings = val;
            return this;
        }
        public Client build() {

            if(authenticationMethods.contains(AuthenticationMethod.CLIENT_SECRET_BASIC) && secret == null){
                throw new ClientBuildException("Selected authentication method: 'CLIENT_SECRET_BASIC'. Secrete can not be NULL.");
            }
            //TODO Validation before return new Client. Need to add for other Builder.
            return new Client(this);
        }
    }
}
