package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserRolesUpdateRequest;

public interface UserRolesRemoveUseCase extends UseCase {
    BaseResponse remove(UserRolesUpdateRequest request);
}
