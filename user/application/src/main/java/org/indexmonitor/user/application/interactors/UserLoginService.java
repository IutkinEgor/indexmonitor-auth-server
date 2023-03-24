package org.indexmonitor.user.application.interactors;

import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserLoginUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserLoginUseCase {

    @Override
    public BaseResponse loginEmail(UserLoginRequest command) {
        return  null;
    }
}
