package org.indexmonitor.client.application.ports.in.client.responses;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientScopesPageResponse {
    private String scopeId;
    private String scopeName;
}
