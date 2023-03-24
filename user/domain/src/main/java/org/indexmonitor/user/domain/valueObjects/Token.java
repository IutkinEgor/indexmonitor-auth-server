package org.indexmonitor.user.domain.valueObjects;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;

import java.time.Instant;
import java.util.Base64;

public class Token {

    //TODO make it jwt token
    private final String tokenHash;
    private final EncryptionAlgorithm algorithm;
    private final Instant issuedAt;
    private final Instant expireAt;
    private Token(Builder builder) {
        tokenHash = builder.tokenHash;
        algorithm = builder.algorithm;
        issuedAt = builder.issuedAt;
        expireAt = builder.expireAt;
    }
    public String getTokenHash() {
        return tokenHash;
    }
    public EncryptionAlgorithm getAlgorithm() {
        return algorithm;
    }
    public Instant getIssuedAt() { return issuedAt; }
    public Instant getExpireAt() { return expireAt; }
    public String getTokenEncoded(){
        return Base64.getEncoder().encodeToString(tokenHash.getBytes());
    }
    public Boolean isExpired() { return this.expireAt.isBefore(Instant.now()); }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String tokenHash;
        private EncryptionAlgorithm algorithm;
        private Instant issuedAt;
        private Instant expireAt;

        private Builder() {
        }

        public Builder tokenHash(String val) {
            tokenHash = val;
            return this;
        }
        public Builder algorithm(EncryptionAlgorithm val) {
            algorithm = val;
            return this;
        }
        public Builder issuedAt(Instant val) {
            issuedAt = val;
            return this;
        }
        public Builder expireAt(Instant val) {
            expireAt = val;
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }
}
