package org.indexmonitor.auth.application.models;

import org.indexmonitor.common.domain.enums.OAuthGrantType;
import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import java.util.Map;
import java.util.Set;

public class Authorization extends AggregateRoot<BaseId> {

    private BaseId registeredClientId; //Record Id
    private String principalName;
    private OAuthGrantType authorizationGrantType;
    private Set<BaseId> authorizedScopesIds;
    private Set<Token> tokens;
    private Map<String, Object> attributes;

    private Authorization(Builder builder) {
        super(builder.id);
        registeredClientId = builder.registeredClientId;
        principalName = builder.principalName;
        authorizationGrantType = builder.authorizationGrantType;
        authorizedScopesIds = builder.authorizedScopesIds;
        tokens = builder.tokens;
        attributes = builder.attributes;
    }

    public BaseId getRegisteredClientId() {
        return registeredClientId;
    }

    public String getRegisteredClientIdAsString() {
        return registeredClientId.getValueAsString();
    }

    public String getPrincipalName() {
        return principalName;
    }

    public OAuthGrantType getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public Set<BaseId> getAuthorizedScopesIds() {
        return authorizedScopesIds;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private BaseId id;
        private BaseId registeredClientId;
        private String principalName;
        private OAuthGrantType authorizationGrantType;
        private Set<BaseId> authorizedScopesIds;
        private Set<Token> tokens;
        private Map<String, Object> attributes;

        private Builder() {
        }

        public Builder id(BaseId val) {
            id = val;
            return this;
        }

        public Builder registeredClientId(BaseId val) {
            registeredClientId = val;
            return this;
        }

        public Builder principalName(String val) {
            principalName = val;
            return this;
        }

        public Builder authorizationGrantType(OAuthGrantType val) {
            authorizationGrantType = val;
            return this;
        }

        public Builder authorizedScopesIds(Set<BaseId> val) {
            authorizedScopesIds = val;
            return this;
        }

        public Builder tokens(Set<Token> val) {
            tokens = val;
            return this;
        }

        public Builder attributes(Map<String, Object> val) {
            attributes = val;
            return this;
        }

        public Authorization build() {
            return new Authorization(this);
        }
    }
}
