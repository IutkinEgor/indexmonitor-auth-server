package org.indexmonitor.client.adapter.out.persistence.mappers.impl;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class ScopePersistenceMapperImpl implements ScopePersistenceMapper {

    @Override
    public ScopeEntity modelToEntity(Scope model) {
        return ScopeEntity.builder()
                .id(ScopeEntity.convertId(model.getId()))
                .name(model.getName())
                .description(model.getDescription() == null ? null : model.getDescription())
                .createdAt(model.getCreatedAt())
                .createdBy(model.getCreatedBy() == null ? null : model.getCreatedBy().getValueAsString())
                .isEnable(model.isEnable())
                .isObtainable(model.isObtainable())
                .build();
    }

    @Override
    public Scope entityToModel(ScopeEntity entity) {
        return Scope.builder()
                .id(new BaseId<UUID>(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy() == null ? null : new BaseId(entity.getCreatedBy()))
                .isEnable(entity.getIsEnable())
                .isObtainable(entity.getIsObtainable())
                .build();
    }
}