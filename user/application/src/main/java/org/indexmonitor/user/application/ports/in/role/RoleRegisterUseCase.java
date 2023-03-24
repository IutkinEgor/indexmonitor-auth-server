package org.indexmonitor.user.application.ports.in.role;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;

public interface RoleRegisterUseCase extends UseCase {
    BaseResponse register(RoleRegisterRequest request);
}
