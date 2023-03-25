package org.indexmonitor.client.adapter.out.persistence.mappers.impl;

import org.indexmonitor.client.adapter.out.persistence.entities.ScopeEntity;
import org.indexmonitor.client.adapter.out.persistence.mappers.ScopePersistenceMapper;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public BasePage<Scope> entityToModel(Page<ScopeEntity> entities) {
        return BasePage.<Scope>builder()
                .elements(entities.getContent().stream().map(entity -> entityToModel(entity)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(entities.getTotalElements())
                .currentPage(entities.getPageable().getPageNumber())
                .currentSize(entities.getPageable().getPageSize())
                .build();
    }
}