package org.indexmonitor.client.adapter.out.persistence.mappers;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.springframework.data.domain.Page;

public interface ScopePersistenceMapper {
    ScopeEntity modelToEntity(Scope model);
    Scope entityToModel(ScopeEntity entity);
    BasePage<Scope> entityToModel(Page<ScopeEntity> entities);
}
