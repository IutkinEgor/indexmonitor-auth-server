package org.indexmonitor.user.application.ports.in.role.responses;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Getter
@Builder
public class RolePageResponse {
    private String roleId;
    private String roleName;
    private Long createdAt;
    private String createdBy;

    public static BasePage<RolePageResponse> map(BasePage<Role> roles){
        return BasePage.<RolePageResponse>builder()
                .elements(roles.getElements().stream()
                        .map(role -> RolePageResponse.builder()
                                .roleId(role.getId().getValueAsString())
                                .roleName(role.getName())
                                .createdAt(role.getCreatedAt().toEpochMilli())
                                .createdBy(role.getCreatedBy() == null ? null : role.getCreatedBy().getValueAsString())
                                .build())
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(roles.getTotalCount())
                .currentPage(roles.getCurrentPage())
                .currentSize(roles.getCurrentSize())
                .build();
    }
}
