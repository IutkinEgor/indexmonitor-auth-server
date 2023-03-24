package org.indexmonitor.client.application.ports.in.scope.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScopeUsedByClientsResponse {
    private String id;
    private String clientId;
    private String name;
}
