package org.indexmonitor.client.application.ports.in.client.responses;

import org.indexmonitor.client.domain.aggregates.Client;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTokenSettingsResponse {
    private String id;
    private Long authorizationCodeTimeToLive;
    private Long accessTokenTimeToLive;
    private Long refreshTokenTimeToLive;
    private boolean reuseRefreshTokens;

    public static ClientTokenSettingsResponse map(Client client){
        return ClientTokenSettingsResponse.builder()
                .id(client.getId().getValue().toString())
                .authorizationCodeTimeToLive(client.getTokenSettings().getAuthorizationCodeTimeToLive().toSeconds())
                .accessTokenTimeToLive(client.getTokenSettings().getAccessTokenTimeToLive().toSeconds())
                .refreshTokenTimeToLive(client.getTokenSettings().getRefreshTokenTimeToLive().toSeconds())
                .reuseRefreshTokens(client.getTokenSettings().isReuseRefreshTokens())
                .build();
    }
}
