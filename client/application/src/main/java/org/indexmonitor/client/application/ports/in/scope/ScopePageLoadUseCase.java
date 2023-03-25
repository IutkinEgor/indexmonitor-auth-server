package org.indexmonitor.client.application.ports.in.scope;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopePageLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;

public interface ScopePageLoadUseCase extends UseCase {
    BasePageResponse<ScopePageResponse> load(ScopePageLoadRequest request);
}
