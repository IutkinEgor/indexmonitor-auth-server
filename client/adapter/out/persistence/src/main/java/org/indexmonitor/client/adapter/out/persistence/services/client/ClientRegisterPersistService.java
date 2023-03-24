package org.indexmonitor.client.adapter.out.persistence.services.client;

import org.indexmonitor.client.adapter.out.persistence.exceptions.ClientExistException;
import org.indexmonitor.client.adapter.out.persistence.mappers.ClientPersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ClientRepository;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientRegisterPort;
import org.indexmonitor.client.domain.aggregates.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ClientRegisterPersistService implements ClientRegisterPort {

    private final ClientLoadPort clientLoadPort;
    private final ClientPersistenceMapper mapper;
    private final ClientRepository repository;


    @Override
    public void register(Client client) {
        if(client == null) {
            throw new NullPointerException("Client is NULL.");
        }
        if(clientLoadPort.isExist(client)) {
            throw new ClientExistException();
        }
        repository.save(mapper.modelToEntity(client));
    }
}
