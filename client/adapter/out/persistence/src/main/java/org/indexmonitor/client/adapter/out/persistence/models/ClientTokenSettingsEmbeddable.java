package org.indexmonitor.client.adapter.out.persistence.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Duration;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientTokenSettingsEmbeddable {
    private Duration authorizationCodeTimeToLive;
    private Duration accessTokenTimeToLive;
    private Duration refreshTokenTimeToLive;
    private boolean reuseRefreshTokens;
    private String tokenSignatureAlgorithm;
}
