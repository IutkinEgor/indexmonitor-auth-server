package org.indexmonitor.user.application.mappers.impl;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.mappers.AuthorityMapper;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityRegisterRequest;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsUpdateRequest;
import org.indexmonitor.user.domain.aggregates.Authority;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public Authority mapRegisterRequest(AuthorityRegisterRequest request, BaseId authorityId) {
        return Authority.builder()
                .id(authorityId)
                .name(request.getName())
                .description(request.getDescription()== null ? null : request.getDescription())
                .createdAt(Instant.now())
                .createdBy(request.getCreatedBy() == null ? null : BaseId.map(request.getCreatedBy()))
                .isEnable(request.getIsEnable())
                .isObtainable(request.getIsObtainable())
                .build();
    }

    @Override
    public Authority mapUpdateRequest(Authority authority, AuthoritySettingsUpdateRequest request) {
        return Authority.builder()
                .id(authority.getId())
                .name(request.getName())
                .description(request.getDescription() == null ? authority.getDescription() : request.getDescription())
                .createdAt(authority.getCreatedAt())
                .createdBy(authority.getCreatedBy() == null ? null : authority.getCreatedBy())
                .isEnable(request.getIsEnable())
                .isObtainable(request.getIsObtainable())
                .build();
    }
}
