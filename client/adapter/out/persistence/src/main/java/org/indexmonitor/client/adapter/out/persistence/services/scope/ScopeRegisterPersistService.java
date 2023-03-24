package org.indexmonitor.client.adapter.out.persistence.services.scope;

import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.adapter.out.persistence.repositories.ScopeRepository;
import org.indexmonitor.client.application.ports.out.scope.ScopeRegisterPort;
import org.indexmonitor.client.domain.aggregates.Scope;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ScopeRegisterPersistService implements ScopeRegisterPort {

    private final ScopePersistenceMapper mapper;
    private final ScopeRepository repository;

    @Override
    public void register(Scope scope) {
        if(scope == null) {
            throw new NullPointerException("Scope is NULL.");
        }
        repository.save(mapper.modelToEntity(scope));
    }
}
