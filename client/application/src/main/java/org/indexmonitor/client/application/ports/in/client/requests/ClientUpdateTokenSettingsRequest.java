package org.indexmonitor.client.application.ports.in.client.requests;

import org.indexmonitor.common.application.models.SelfValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.time.Duration;

@Setter
@NoArgsConstructor
public class ClientUpdateTokenSettingsRequest extends SelfValidator<ClientUpdateTokenSettingsRequest> {
    @NotBlank(message = "Client record Id can not be empty.")
    @UUID(message = "Illegal Id format.")
    private String id;
    @NotNull(message = "Authorization code time to live can not be empty")
    @Min(value = 0, message = "Authorization code time to live can not be less than 0 second.")
    private Long authorizationCodeTimeToLive;
    @NotNull(message = "Access token  time to live can not be empty")
    @Min(value = 0, message = "Access token time to live can not be less than 0.")
    private Long accessTokenTimeToLive;
    @NotNull(message = "Refresh token  time to live can not be empty")
    @Min(value = 0, message = "Refresh token time to live can not be less than 0.")
    private Long refreshTokenTimeToLive;
    @NotNull(message = "Reuse refresh token must be defined")
    private Boolean reuseRefreshTokens;

    public java.util.UUID getId() {
        return java.util.UUID.fromString(id);
    }

    public Duration getAuthorizationCodeTimeToLive() {
        return Duration.ofSeconds(authorizationCodeTimeToLive);
    }

    public Duration getAccessTokenTimeToLive() {
        return Duration.ofSeconds(accessTokenTimeToLive);
    }

    public Duration getRefreshTokenTimeToLive() {
        return Duration.ofSeconds(refreshTokenTimeToLive);
    }

    public boolean isReuseRefreshTokens() {
        return reuseRefreshTokens;
    }
}
