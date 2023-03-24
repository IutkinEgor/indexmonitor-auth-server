package org.indexmonitor.user.application.ports.in.role;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.role.requests.RoleDeleteRequest;

public interface RoleDeleteUseCase extends UseCase {
    BaseResponse delete(RoleDeleteRequest request);
}
