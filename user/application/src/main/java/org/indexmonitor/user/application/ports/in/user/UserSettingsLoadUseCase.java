package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserSettingsResponse;

public interface UserSettingsLoadUseCase extends UseCase {
    BaseResponse<UserSettingsResponse> load(UserLoadRequest request);
}
