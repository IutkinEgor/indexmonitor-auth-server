package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserDeleteUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserDeleteRequest;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailDeletePort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailLoadPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordDeletePort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserDeletePort;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserDeleteInteractor extends Interactor implements UserDeleteUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserDeleteInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserDeletePort userDeletePort;
    private final ResetPasswordLoadPort resetPasswordLoadPort;
    private final ResetPasswordDeletePort resetPasswordDeletePort;
    private final ConfirmEmailLoadPort confirmEmailLoadPort;
    private final ConfirmEmailDeletePort confirmEmailDeletePort;

    @Override
    public BaseResponse delete(UserDeleteRequest request) {
        try {
            request.validateSelf();
            tryDeleteReferences(request);
            tryDeleteUser(request);
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to delete user with ID '%s'. Exception message: '%s'.",  request.getId() == null ? "null" : request.getId(), e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private void tryDeleteUser(UserDeleteRequest request){
        if(userLoadPort.findByUserId(BaseId.map(request.getId())).isEmpty()){
            throw new UserNotFoundException();
        }
        userDeletePort.delete(BaseId.map(request.getId()));
    }

    private void tryDeleteReferences(UserDeleteRequest request){
        if(resetPasswordLoadPort.findByUserId(BaseId.map(request.getId())).isPresent()){
            resetPasswordDeletePort.deleteById(BaseId.map(request.getId()));
        }
        if(confirmEmailLoadPort.findByUserId(BaseId.map(request.getId())).isPresent()){
            confirmEmailDeletePort.deleteById(BaseId.map(request.getId()));
        }
    }
}
