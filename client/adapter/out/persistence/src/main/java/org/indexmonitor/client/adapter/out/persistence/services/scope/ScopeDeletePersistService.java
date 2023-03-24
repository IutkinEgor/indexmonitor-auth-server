package org.indexmonitor.client.adapter.out.persistence.services.scope;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.repositories.ScopeRepository;
import org.indexmonitor.client.application.ports.out.scope.ScopeDeletePort;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ScopeDeletePersistService implements ScopeDeletePort {

    private final ScopeRepository repository;

    @Override
    public void delete(BaseId id) {
        if(id == null){
            throw new NullPointerException("ID is NULL.");
        }
        repository.deleteById(ScopeEntity.convertId(id));
    }
}
