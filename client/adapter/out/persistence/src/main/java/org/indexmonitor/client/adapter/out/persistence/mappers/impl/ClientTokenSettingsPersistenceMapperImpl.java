package org.indexmonitor.client.adapter.out.persistence.mappers.impl;

import org.indexmonitor.client.adapter.out.persistence.mappers.ClientTokenSettingsPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.models.ClientTokenSettingsEmbeddable;
import org.indexmonitor.client.domain.valueObjects.ClientTokenSettings;
import org.springframework.stereotype.Component;

@Component
class ClientTokenSettingsPersistenceMapperImpl implements ClientTokenSettingsPersistenceMapper {
    @Override
    public ClientTokenSettingsEmbeddable modelToEntity(ClientTokenSettings model) {
        return ClientTokenSettingsEmbeddable.builder()
                .authorizationCodeTimeToLive(model.getAuthorizationCodeTimeToLive())
                .accessTokenTimeToLive(model.getAccessTokenTimeToLive())
                .refreshTokenTimeToLive(model.getRefreshTokenTimeToLive())
                .reuseRefreshTokens(model.isReuseRefreshTokens())
                .tokenSignatureAlgorithm(model.getTokenSignatureAlgorithm())
                .build();
    }

    @Override
    public ClientTokenSettings entityToModel(ClientTokenSettingsEmbeddable entity) {
        return ClientTokenSettings.builder()
                .authorizationCodeTimeToLive(entity.getAuthorizationCodeTimeToLive())
                .accessTokenTimeToLive(entity.getAccessTokenTimeToLive())
                .refreshTokenTimeToLive(entity.getRefreshTokenTimeToLive())
                .reuseRefreshTokens(entity.isReuseRefreshTokens())
                .tokenSignatureAlgorithm(entity.getTokenSignatureAlgorithm())
                .build();
    }
}
