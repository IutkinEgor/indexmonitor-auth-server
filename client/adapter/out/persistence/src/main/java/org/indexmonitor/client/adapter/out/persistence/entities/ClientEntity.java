package org.indexmonitor.client.adapter.out.persistence.entities;

import org.indexmonitor.client.adapter.out.persistence.exceptions.ScopeIllegalIdException;
import org.indexmonitor.client.adapter.out.persistence.models.ClientSettingsEmbeddable;
import org.indexmonitor.client.adapter.out.persistence.models.ClientTokenSettingsEmbeddable;
import org.indexmonitor.client.domain.enums.AuthenticationMethod;
import org.indexmonitor.client.domain.enums.OAuthGrantType;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity{
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;
    @Column(nullable = false)
    private Instant issuedAt;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true)
    private String secretHash;
    private EncryptionAlgorithm secretAlgorithm;
    @Column(length = 2000)
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_methods")
    private Set<AuthenticationMethod> authenticationMethods;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "grant_types")
    private Set<OAuthGrantType> authorizationGrantTypes;
    @Column(unique = true)
    private String origin;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "redirect_uris")
    private Set<String> redirectUris;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_scopes",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<ScopeEntity> scopes;
    @Embedded
    private ClientSettingsEmbeddable clientSettings;
    @Embedded
    private ClientTokenSettingsEmbeddable tokenSettings;

    public BaseId<UUID> getBaseId() {
        return new BaseId<UUID>(this.id);
    }

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new ScopeIllegalIdException();
        }
    }
    private ClientEntity(Builder builder) {
        setId(builder.id);
        setClientId(builder.clientId);
        setIssuedAt(builder.issuedAt);
        setName(builder.name);
        setSecretHash(builder.secretHash);
        setSecretAlgorithm(builder.secretAlgorithm);
        setDescription(builder.description);
        setAuthenticationMethods(builder.authenticationMethods);
        setAuthorizationGrantTypes(builder.authorizationGrantTypes);
        setOrigin(builder.origin);
        setRedirectUris(builder.redirectUris);
        setScopes(builder.scopes);
        setClientSettings(builder.clientSettings);
        setTokenSettings(builder.tokenSettings);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String clientId;
        private Instant issuedAt;
        private String name;
        private String secretHash;
        private EncryptionAlgorithm secretAlgorithm;
        private String description;
        private Set<AuthenticationMethod> authenticationMethods;
        private Set<OAuthGrantType> authorizationGrantTypes;
        private String origin;
        private Set<String> redirectUris;
        private Set<ScopeEntity> scopes;
        private ClientSettingsEmbeddable clientSettings;
        private ClientTokenSettingsEmbeddable tokenSettings;

        private Builder() {
        }
        public Builder id(UUID val) {
            id = val;
            return this;
        }
        public Builder id(BaseId id) {
            if(!(id.getValue() instanceof UUID)){
                throw new IllegalArgumentException("Client Id has wrong format. Expected format - UUID. Got value: " + id.getValue());
            }
            this.id = (UUID) id.getValue();
            return this;
        }
        public Builder clientId(String val) {
            clientId = val;
            return this;
        }
        public Builder issuedAt(Instant val) {
            issuedAt = val;
            return this;
        }
        public Builder name(String val) {
            name = val;
            return this;
        }
        public Builder secretHash(String val) {
            secretHash = val;
            return this;
        }
        public Builder secretAlgorithm(EncryptionAlgorithm val) {
            secretAlgorithm = val;
            return this;
        }
        public Builder description(String val) {
            description = val;
            return this;
        }
        public Builder authenticationMethods(Set<AuthenticationMethod> val) {
            authenticationMethods = val;
            return this;
        }
        public Builder authorizationGrantTypes(Set<OAuthGrantType> val) {
            authorizationGrantTypes = val;
            return this;
        }
        public Builder origin(String val) {
            origin = val;
            return this;
        }
        public Builder redirectUris(Set<String> val) {
            redirectUris = val;
            return this;
        }
        public Builder scopes(Set<ScopeEntity> val) {
            scopes = val;
            return this;
        }
        public Builder clientSettings(ClientSettingsEmbeddable val) {
            clientSettings = val;
            return this;
        }
        public Builder tokenSettings(ClientTokenSettingsEmbeddable val) {
            tokenSettings = val;
            return this;
        }
        public ClientEntity build() {
            return new ClientEntity(this);
        }
    }
}

