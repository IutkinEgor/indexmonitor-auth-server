package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.AuthorityEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.AuthorityPersistenceMapper;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class AuthorityPersistenceMapperImpl implements AuthorityPersistenceMapper {

    @Override
    public AuthorityEntity modelToEntity(Authority model) {
        return AuthorityEntity.builder()
                .id(AuthorityEntity.convertId(model.getId()))
                .name(model.getName())
                .description(model.getDescription() == null ? null : model.getDescription())
                .createdAt(model.getCreatedAt())
                .createdBy(model.getCreatedBy() == null ? null : model.getCreatedBy().getValueAsString())
                .isEnable(model.isEnable())
                .isObtainable(model.isObtainable())
                .build();
    }

    @Override
    public Authority entityToModel(AuthorityEntity entity) {
        return Authority.builder()
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
    public BasePage<Authority> entityToModel(Page<AuthorityEntity> entities) {
        return BasePage.<Authority>builder()
                .elements(entities.getContent().stream().map(entity -> entityToModel(entity)).collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(entities.getTotalElements())
                .currentPage(entities.getPageable().getPageNumber())
                .currentSize(entities.getPageable().getPageSize())
                .build();
    }
}
