package org.indexmonitor.client.application.ports.in.scope;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopeUpdateRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ScopeUpdateUseCase extends UseCase {
    BaseResponse update(ScopeUpdateRequest request);
}
