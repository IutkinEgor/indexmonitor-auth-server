package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetStartRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserPasswordResetResponse;

public interface UserPasswordResetUseCase extends UseCase {

    BaseResponse<UserPasswordResetResponse> loadUserInfo(UserPasswordResetStartRequest request);

    BaseResponse sendToken(UserPasswordResetRequest request);

    BaseResponse validateToken(String token);

    BaseResponse updatePassword(UserPasswordResetCallbackRequest request);
}
