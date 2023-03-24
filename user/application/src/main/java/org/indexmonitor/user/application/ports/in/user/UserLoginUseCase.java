package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;

public interface UserLoginUseCase {

    BaseResponse loginEmail(UserLoginRequest command);
}
