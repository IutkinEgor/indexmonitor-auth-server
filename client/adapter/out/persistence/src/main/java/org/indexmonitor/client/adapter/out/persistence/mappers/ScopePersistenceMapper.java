package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.domain.aggregates.Scope;

public interface ScopePersistenceMapper {
    ScopeEntity modelToEntity(Scope model);
    Scope entityToModel(ScopeEntity entity);
}
