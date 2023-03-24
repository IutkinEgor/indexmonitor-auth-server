package org.indexmonitor.user.application.ports.in.authority.responses;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.Builder;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Getter
@Builder
public class AuthorityPageResponse {
    private String authorityId;
    private String authorityName;
    private Long createdAt;
    private String createdBy;
    
    public static BasePage<AuthorityPageResponse> map(BasePage<Authority> authorities){
        return BasePage.<AuthorityPageResponse>builder()
                .elements(authorities.getElements().stream()
                        .map(authority -> AuthorityPageResponse.builder()
                                .authorityId(authority.getId().getValueAsString())
                                .authorityName(authority.getName())
                                .createdAt(authority.getCreatedAt().toEpochMilli())
                                .createdBy(authority.getCreatedBy() == null ? null : authority.getCreatedBy().getValueAsString())
                                .build())
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(authorities.getTotalCount())
                .currentPage(authorities.getCurrentPage())
                .currentSize(authorities.getCurrentSize())
                .build();
    }
}
