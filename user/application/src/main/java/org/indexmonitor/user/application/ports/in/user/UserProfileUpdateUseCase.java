package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserProfileUpdateRequest;

public interface UserProfileUpdateUseCase extends UseCase {
    BaseResponse update(UserProfileUpdateRequest request);
}
