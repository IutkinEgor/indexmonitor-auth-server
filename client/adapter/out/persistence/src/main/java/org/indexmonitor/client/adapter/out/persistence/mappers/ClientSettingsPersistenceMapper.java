package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.models.ClientSettingsEmbeddable;
import org.indexmonitor.client.domain.valueObjects.ClientSettings;

public interface ClientSettingsPersistenceMapper {
    ClientSettingsEmbeddable modelToEntity(ClientSettings model);
    ClientSettings entityToModel(ClientSettingsEmbeddable entity);
}
