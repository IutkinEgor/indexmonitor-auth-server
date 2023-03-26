package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.ports.in.user.UserLoginUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserLoginInteractor extends Interactor implements UserLoginUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserLoginInteractor.class);
    @Override
    public BaseResponse loginEmail(UserLoginRequest request) {
        try{
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to login user. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }
}
