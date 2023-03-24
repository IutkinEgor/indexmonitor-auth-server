package org.indexmonitor.user.application.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.mappers.RoleMapper;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsUpdateRequest;
import org.indexmonitor.user.domain.aggregates.Role;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
class RoleMapperImpl implements RoleMapper {
    @Override
    public Role mapRegisterRequest(RoleRegisterRequest request, BaseId roleId) {
        return Role.builder()
                .id(roleId)
                .name(request.getName())
                .description(request.getDescription()== null ? null : request.getDescription())
                .createdAt(Instant.now())
                .createdBy(request.getCreatedBy() == null ? null : BaseId.map(request.getCreatedBy()))
                .isEnable(request.getIsEnable())
                .isObtainable(request.getIsObtainable())
                .build();
    }

    @Override
    public Role mapUpdateRequest(Role role, RoleSettingsUpdateRequest request) {
        return Role.builder()
                .id(role.getId())
                .name(request.getName())
                .description(request.getDescription() == null ? role.getDescription() : request.getDescription())
                .createdAt(role.getCreatedAt())
                .createdBy(role.getCreatedBy() == null ? null : role.getCreatedBy())
                .isEnable(request.getIsEnable())
                .isObtainable(request.getIsObtainable())
                .build();
    }
}
