package org.indexmonitor.client.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ClientTokenSettings extends ValueObject {

    private final Duration authorizationCodeTimeToLive;
    private final Duration accessTokenTimeToLive;
    private final Duration refreshTokenTimeToLive;
    private final boolean reuseRefreshTokens;
    private final String tokenSignatureAlgorithm;

    private ClientTokenSettings(Builder builder) {
        authorizationCodeTimeToLive = builder.authorizationCodeTimeToLive;
        accessTokenTimeToLive = builder.accessTokenTimeToLive;
        refreshTokenTimeToLive = builder.refreshTokenTimeToLive;
        reuseRefreshTokens = builder.reuseRefreshTokens;
        tokenSignatureAlgorithm = builder.tokenSignatureAlgorithm;
    }

    public static ClientTokenSettings withDefault(){
        return ClientTokenSettings.builder()
                .authorizationCodeTimeToLive(Duration.ofMinutes(5))
                .accessTokenTimeToLive(Duration.ofMinutes(5))
                .refreshTokenTimeToLive(Duration.ofMinutes(60))
                .reuseRefreshTokens(true)
                .tokenSignatureAlgorithm("RS256")
                .build();
    }

    public Duration getAuthorizationCodeTimeToLive() {
        return authorizationCodeTimeToLive;
    }

    public Duration getAccessTokenTimeToLive() {
        return accessTokenTimeToLive;
    }

    public Duration getRefreshTokenTimeToLive() {
        return refreshTokenTimeToLive;
    }

    public boolean isReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public String getTokenSignatureAlgorithm() {
        return tokenSignatureAlgorithm;
    }

    public Map<String, Object> getSettings(){
        Map<String,Object> map = new HashMap<>();
        map.put("authorizationCodeTimeToLive", authorizationCodeTimeToLive);
        map.put("accessTokenTimeToLive",accessTokenTimeToLive);
        map.put("refreshTokenTimeToLive",refreshTokenTimeToLive);
        map.put("reuseRefreshTokens",reuseRefreshTokens);
        map.put("tokenSignatureAlgorithm",tokenSignatureAlgorithm);
        return map;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Duration authorizationCodeTimeToLive;
        private Duration accessTokenTimeToLive;
        private Duration refreshTokenTimeToLive;
        private boolean reuseRefreshTokens;
        private String tokenSignatureAlgorithm;

        private Builder() {
        }

        public Builder authorizationCodeTimeToLive(Duration val) {
            authorizationCodeTimeToLive = val;
            return this;
        }

        public Builder accessTokenTimeToLive(Duration val) {
            accessTokenTimeToLive = val;
            return this;
        }

        public Builder refreshTokenTimeToLive(Duration val) {
            refreshTokenTimeToLive = val;
            return this;
        }

        public Builder reuseRefreshTokens(boolean val) {
            reuseRefreshTokens = val;
            return this;
        }

        public Builder tokenSignatureAlgorithm(String val) {
            tokenSignatureAlgorithm = val;
            return this;
        }
        public ClientTokenSettings build() {
            return new ClientTokenSettings(this);
        }
    }
}
