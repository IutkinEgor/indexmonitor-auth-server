package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;

public interface UserRegisterUseCase extends UseCase {
    BaseResponse register(UserRegisterRequest command);

    BaseResponse<RedirectUrlResponse> confirmEmailCallback(String token);
}
