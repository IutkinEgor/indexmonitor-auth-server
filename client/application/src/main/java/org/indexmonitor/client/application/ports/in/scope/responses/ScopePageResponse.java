package org.indexmonitor.client.application.ports.in.scope.responses;


import org.indexmonitor.client.domain.aggregates.Scope;
import lombok.*;
import org.indexmonitor.common.domain.valueObjects.BasePage;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ScopePageResponse {
    private String scopeId;
    private String scopeName;
    private Long createdAt;
    private String createdBy;

    public static BasePage<ScopePageResponse> map(BasePage<Scope> scopes){
        return BasePage.<ScopePageResponse>builder()
                .elements(scopes.getElements().stream()
                        .map(role -> ScopePageResponse.builder()
                                .scopeId(role.getId().getValueAsString())
                                .scopeName(role.getName())
                                .createdAt(role.getCreatedAt().toEpochMilli())
                                .createdBy(role.getCreatedBy() == null ? null : role.getCreatedBy().getValueAsString())
                                .build())
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(scopes.getTotalCount())
                .currentPage(scopes.getCurrentPage())
                .currentSize(scopes.getCurrentSize())
                .build();
    }
}
