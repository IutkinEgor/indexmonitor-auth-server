package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateScopeRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ClientScopeRemoveUseCase extends UseCase {
    BaseResponse remove(ClientUpdateScopeRequest request);
}
