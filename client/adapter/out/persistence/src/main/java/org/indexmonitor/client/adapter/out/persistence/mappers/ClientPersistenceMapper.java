package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.indexmonitor.client.domain.aggregates.Client;

public interface ClientPersistenceMapper {
    ClientEntity modelToEntity(Client model);
    Client entityToModel(ClientEntity entity);
}
