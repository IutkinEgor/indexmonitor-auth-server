package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordChangeCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordChangeRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;

public interface UserPasswordChangeUseCase extends UseCase {

    BaseResponse verifyCredentials(UserPasswordChangeRequest request);
    BaseResponse<BaseId> validateToken(String token);

    BaseResponse updatePassword(UserPasswordChangeCallbackRequest request);

}
