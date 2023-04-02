package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.entities.ClientEntity;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.springframework.data.domain.Page;

public interface ClientPersistenceMapper {
    ClientEntity modelToEntity(Client model);
    Client entityToModel(ClientEntity entity);
    BasePage<Client> entityToModel(Page<ClientEntity> entities);
}
