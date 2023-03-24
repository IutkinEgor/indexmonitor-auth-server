package org.indexmonitor.client.adapter.out.persistence.mappers.impl;

import org.indexmonitor.client.adapter.out.persistence.mappers.ClientSettingsPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.models.ClientSettingsEmbeddable;
import org.indexmonitor.client.domain.valueObjects.ClientSettings;
import org.springframework.stereotype.Component;

@Component
class ClientSettingsPersistenceMapperImpl implements ClientSettingsPersistenceMapper {
    @Override
    public ClientSettingsEmbeddable modelToEntity(ClientSettings model) {
        return new ClientSettingsEmbeddable(model.isRequireProofKey(),model.isRequireAuthorizationConsent());
    }

    @Override
    public ClientSettings entityToModel(ClientSettingsEmbeddable entity) {
        return new ClientSettings(entity.isRequireProofKey(),entity.isRequireAuthorizationConsent());
    }
}
