package org.indexmonitor.user.application.ports.in.user;

import org.indexmonitor.common.domain.interfaces.UseCase;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordRegisterRequest;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordStartRequest;
import org.indexmonitor.user.application.ports.in.user.requests.ResetPasswordUpdateRequest;

public interface ResetPasswordUseCase extends UseCase {

    BaseResponse<ResetPasswordRegisterRequest> start(ResetPasswordStartRequest command);

    BaseResponse sendToken(ResetPasswordRegisterRequest command);

    BaseResponse validateToken(String token);

    BaseResponse updatePassword(ResetPasswordUpdateRequest command);
}
