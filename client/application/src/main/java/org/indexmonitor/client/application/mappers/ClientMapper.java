package org.indexmonitor.client.application.mappers;

import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateRequest;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.client.domain.valueObjects.ClientSecret;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.util.Set;
public interface ClientMapper {

    Client mapRegisterRequest(ClientRegisterRequest request, BaseId id, ClientSecret secret, Set<Scope> scope);
    Client mapUpdateRequest(Client client, ClientUpdateRequest request);
}
