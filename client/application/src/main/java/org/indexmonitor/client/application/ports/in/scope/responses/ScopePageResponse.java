package org.indexmonitor.client.application.ports.in.scope.responses;


import org.indexmonitor.client.domain.aggregates.Scope;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ScopePageResponse {
    private String id;
    private String name;
    private Long createdAt;
    private String createdBy;

    public static Set<ScopePageResponse> map(Set<Scope> scopes){
        return scopes.stream().map(scope -> ScopePageResponse.builder()
                .id(scope.getId().getValueAsString())
                .name(scope.getName())
                .createdAt(scope.getCreatedAt().toEpochMilli())
                .createdBy(scope.getCreatedBy() == null ? null : scope.getCreatedBy().getValueAsString())
                .build()
        ).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
