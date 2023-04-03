package org.indexmonitor.client.application.ports.in.client.responses;

import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.client.domain.aggregates.Client;
import lombok.Builder;
import lombok.Getter;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ClientPageModelResponse {
    private String id;
    private String clientId;
    private Long issuedAt;
    private String name;
    private String origin;

    public static BasePage<ClientPageModelResponse> map(BasePage<Client> clients){
        return BasePage.<ClientPageModelResponse>builder()
                .elements(clients.getElements().stream()
                        .map(client -> ClientPageModelResponse.builder()
                                .id(client.getId().getValueAsString())
                                .clientId(client.getClientId())
                                .name(client.getName())
                                .issuedAt(client.getIssuedAt().toEpochMilli())
                                .origin(client.getOrigin().toString())
                                .build())
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .totalCount(clients.getTotalCount())
                .currentPage(clients.getCurrentPage())
                .currentSize(clients.getCurrentSize())
                .build();
    }
}
