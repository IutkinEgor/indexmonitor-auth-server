package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserSettingsUpdateRequest;

public interface UserSettingsUpdateUseCase extends UseCase {
    BaseResponse update(UserSettingsUpdateRequest request);
}
