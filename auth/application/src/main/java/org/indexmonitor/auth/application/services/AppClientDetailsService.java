package org.indexmonitor.auth.application.services;

import org.indexmonitor.auth.application.mappers.RegisteredClientMapper;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppClientDetailsService implements RegisteredClientRepository {
    private final ClientLoadPort clientLoadPort;
    private final RegisteredClientMapper registeredClientMapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        //Save operation permitted only from Application layer;
    }

    @Override
    public RegisteredClient findById(String id) {
        Optional<Client> client = clientLoadPort.findById(BaseId.map(id));
        if(client.isEmpty()){
            return null;
        }
        return registeredClientMapper.map(client.get());
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<Client> client = clientLoadPort.findByClientId(clientId);
        if(client.isEmpty()){
            return null;
        }
        return registeredClientMapper.map(client.get());
    }
}
