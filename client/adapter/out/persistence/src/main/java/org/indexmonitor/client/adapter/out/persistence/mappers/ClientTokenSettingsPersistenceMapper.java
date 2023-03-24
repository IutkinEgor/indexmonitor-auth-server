package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.models.ClientTokenSettingsEmbeddable;
import org.indexmonitor.client.domain.valueObjects.ClientTokenSettings;

public interface ClientTokenSettingsPersistenceMapper {
    ClientTokenSettingsEmbeddable modelToEntity(ClientTokenSettings model);
    ClientTokenSettings entityToModel(ClientTokenSettingsEmbeddable entity);
}
