package org.indexmonitor.client.application.ports.in.scope.responses;

import org.indexmonitor.client.domain.aggregates.Scope;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScopeResponse {
    private String id;
    private String name;
    private String description;
    private Long createdAt;
    private String createdBy;
    private Boolean isEnable;
    private Boolean isObtainable;

    public static ScopeResponse map(Scope scope){
        return ScopeResponse.builder()
                .id(scope.getId().getValue().toString())
                .name(scope.getName())
                .description((scope.getDescription() == null || scope.getDescription().isEmpty())? null : scope.getDescription())
                .createdAt(scope.getCreatedAt().toEpochMilli())
                .createdBy(scope.getCreatedBy() == null ? null : scope.getCreatedBy().getValueAsString())
                .isEnable(scope.isEnable())
                .isObtainable(scope.isObtainable())
                .build();
    }
}
