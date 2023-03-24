package org.indexmonitor.client.application.ports.in.scope;

import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;

public interface ScopeRegisterUseCase extends UseCase {
    BaseResponse register(ScopeRegisterRequest request);
}
