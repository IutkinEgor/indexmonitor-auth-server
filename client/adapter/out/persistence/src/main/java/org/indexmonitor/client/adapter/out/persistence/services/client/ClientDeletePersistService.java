package org.indexmonitor.client.adapter.out.persistence.services.client;

import org.indexmonitor.client.adapter.out.persistence.repositories.ClientRepository;
import org.indexmonitor.client.application.ports.out.client.ClientDeletePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class ClientDeletePersistService implements ClientDeletePort {

    private final ClientRepository repository;
    @Override
    public void delete(UUID id) {
        if(id == null){
            throw new NullPointerException("ID is NULL.");
        }
        repository.deleteById(id);
    }
}
