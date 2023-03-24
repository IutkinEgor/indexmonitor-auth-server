package org.indexmonitor.user.application.ports.in.role.responses;

import org.indexmonitor.user.domain.aggregates.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoleResponse {
    private String id;
    private String name;
    private String description;
    private Long createdAt;
    private String createdBy;
    private Boolean isEnable;
    private Boolean isObtainable;


    public static RoleResponse map(Role role){
        return RoleResponse.builder()
                .id(role.getId().getValueAsString())
                .createdAt(role.getCreatedAt().toEpochMilli())
                .createdBy(role.getCreatedBy() == null ? null : role.getCreatedBy().getValueAsString())
                .name(role.getName())
                .description(role.getDescription() == null ? null : role.getDescription())
                .isEnable(role.isEnable())
                .isObtainable(role.isObtainable())
                .build();
    }
}
