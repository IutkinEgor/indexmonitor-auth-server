package org.indexmonitor.client.application.ports.in.scope;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopeDeleteRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ScopeDeleteUseCase extends UseCase {
    BaseResponse delete(ScopeDeleteRequest request);
}
