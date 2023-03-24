package org.indexmonitor.client.application.ports.in.scope;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadPageRequest;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeResponse;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeUsedByClientsResponse;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

import java.util.Set;

public interface ScopeLoadUseCase extends UseCase {
    BaseResponse<Set<ScopePageResponse>> findAll(ScopeLoadPageRequest request);
    BaseResponse<Set<ScopeUsedByClientsResponse>> findAllScopeClients(ScopeLoadRequest request);
    BaseResponse<ScopeResponse> findById(ScopeLoadRequest request);
}
