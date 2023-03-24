package org.indexmonitor.client.adapter.out.persistence.services.scope;

import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ScopeRepository;
import org.indexmonitor.client.application.ports.out.scope.ScopeUpdatePort;
import org.indexmonitor.client.domain.aggregates.Scope;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScopeUpdatePersistService implements ScopeUpdatePort {
    private final ScopePersistenceMapper mapper;
    private final ScopeRepository repository;

    @Override
    public void update(Scope scope) {
        if(scope == null) {
            throw new NullPointerException("Scope is NULL.");
        }
        repository.save(mapper.modelToEntity(scope));
    }
}
