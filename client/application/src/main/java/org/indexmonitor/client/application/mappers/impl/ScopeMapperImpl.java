package org.indexmonitor.client.application.mappers.impl;

import org.indexmonitor.client.application.mappers.ScopeMapper;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeUpdateRequest;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.springframework.stereotype.Component;

@Component
class ScopeMapperImpl implements ScopeMapper {

    @Override
    public Scope mapRegisterRequest(ScopeRegisterRequest request, BaseId id) {
        return Scope.builder()
                .id(id)
                .name(request.getName().toLowerCase())
                .description((request.getDescription() == null || request.getDescription().isEmpty())? null : request.getDescription())
                .createdAt(request.getCreatedAt())
                .createdBy(request.getCreatedBy() == null ? null : BaseId.map(request.getCreatedBy()))
                .isEnable(true)
                .isObtainable(true)
                .build();
    }

    @Override
    public Scope mapUpdateRequest(Scope scope, ScopeUpdateRequest request) {
        return Scope.builder()
                .id(scope.getId())
                .name(request.getName().toLowerCase())
                .description((request.getDescription() == null || request.getDescription().isEmpty())? null : request.getDescription())
                .createdAt(scope.getCreatedAt())
                .createdBy(scope.getCreatedBy() == null ? null : scope.getCreatedBy())
                .isEnable(request.getIsEnable())
                .isObtainable(request.getIsObtainable())
                .build();
    }
}
