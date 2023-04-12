package org.indexmonitor.auth.application.models;

import org.indexmonitor.common.domain.models.ValueObject;

import java.io.Serializable;
import java.time.Instant;

public class Token extends ValueObject implements Serializable {
    private final String token;
    private final Instant issuedAt;
    private final Instant expireAt;

    public Token(String token, Instant issuedAt, Instant expireAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expireAt = expireAt;
    }

    public String getToken() {
        return token;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public Instant getExpiresAt() {
        return expireAt;
    }
}
