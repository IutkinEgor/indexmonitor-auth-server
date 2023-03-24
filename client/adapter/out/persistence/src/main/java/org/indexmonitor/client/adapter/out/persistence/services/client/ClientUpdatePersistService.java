package org.indexmonitor.client.adapter.out.persistence.services.client;

import org.indexmonitor.client.adapter.out.persistence.mappers.ClientPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ClientRepository;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientUpdatePort;
import org.indexmonitor.client.domain.aggregates.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientUpdatePersistService implements ClientUpdatePort {
    private final ClientLoadPort clientLoadPort;
    private final ClientPersistenceMapper mapper;
    private final ClientRepository repository;

    @Override
    public void update(Client client) {
        if(client == null) {
            throw new NullPointerException("Client is NULL.");
        }
        repository.save(mapper.modelToEntity(client));
    }
}
