package org.indexmonitor.client.application.ports.out.client;

import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;

import java.util.Optional;
import java.util.Set;

public interface ClientLoadPort extends Port {
    BasePage<Client> findAll(Integer offset, Integer limit);
    Set<Client> findAllByScopeId(BaseId id);
    Optional<Client> findById(BaseId id);
    Optional<Client> findByClientId(String id);
    Optional<Client> findByName(String name);
    Optional<Client> findByOrigin(String origin);
    Optional<Set<Scope>> findScopes(BaseId id);
    Boolean isExist(Client client);
    Boolean isExistMoreThanOne(Client client);
    Boolean isExistById(BaseId id);

}
