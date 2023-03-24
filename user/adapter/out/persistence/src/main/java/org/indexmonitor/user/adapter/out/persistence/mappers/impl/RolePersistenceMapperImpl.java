package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.RolePersistenceMapper;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class RolePersistenceMapperImpl implements RolePersistenceMapper {

    @Override
    public RoleEntity modelToEntity(Role model) {
        return RoleEntity.builder()
                .id(RoleEntity.convertId(model.getId()))
                .name(model.getName())
                .description(model.getDescription() == null ? null : model.getDescription())
                .createdAt(model.getCreatedAt())
                .createdBy(model.getCreatedBy() == null ? null : model.getCreatedBy().getValueAsString())
                .isEnable(model.isEnable())
                .isObtainable(model.isObtainable())
                .build();
    }

    @Override
    public Role entityToModel(RoleEntity entity) {
        return Role.builder()
                .id(BaseId.map(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy() == null ? null : BaseId.map(entity.getCreatedBy()))
                .isEnable(entity.getIsEnable())
                .isObtainable(entity.getIsObtainable())
                .build();
    }

    @Override
    public BasePage<Role> entityToModel(Page<RoleEntity> entities) {
        return BasePage.<Role>builder()
                .elements(entities.getContent().stream().map(entity -> entityToModel(entity)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(entities.getTotalElements())
                .currentPage(entities.getPageable().getPageNumber())
                .currentSize(entities.getPageable().getPageSize())
                .build();
    }
}
