package org.indexmonitor.user.application.ports.in.authority.responses;

import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorityResponse {
    private String id;
    private String name;
    private String description;
    private Long createdAt;
    private String createdBy;
    private Boolean isEnable;
    private Boolean isObtainable;

    public static AuthorityResponse map(Authority authority){
        return AuthorityResponse.builder()
                .id(authority.getId().getValueAsString())
                .createdAt(authority.getCreatedAt().toEpochMilli())
                .createdBy(authority.getCreatedBy() == null ? null : authority.getCreatedBy().getValueAsString())
                .name(authority.getName())
                .description(authority.getDescription() == null ? null : authority.getDescription())
                .isEnable(authority.isEnable())
                .isObtainable(authority.isObtainable())
                .build();
    }
}
