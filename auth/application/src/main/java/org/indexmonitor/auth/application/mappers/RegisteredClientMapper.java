package org.indexmonitor.auth.application.mappers;

import org.indexmonitor.client.domain.aggregates.Client;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface RegisteredClientMapper {

    RegisteredClient map(Client client);
    Client map(RegisteredClient client);
}
