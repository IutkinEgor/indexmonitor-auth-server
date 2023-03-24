package org.indexmonitor.client.application.ports.in.client;

import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientScopesPageResponse;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

import java.util.Set;

public interface ClientScopeLoadUseCase extends UseCase {
    BaseResponse<Set<ClientScopesPageResponse>> findById(ClientLoadRequest request);
}
