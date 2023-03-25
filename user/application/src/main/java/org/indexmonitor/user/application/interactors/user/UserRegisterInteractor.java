package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserExistException;
import org.indexmonitor.user.application.mappers.UserMapper;
import org.indexmonitor.user.application.ports.in.user.UserRegisterUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserRegisterPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.application.services.ConfirmEmailService;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.Recovery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRegisterInteractor extends Interactor implements UserRegisterUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserRegisterInteractor.class);
    @Value("${app.email.enable}")
    private Boolean isEmailServiceEnable;
    private final UserRegisterPort userRegisterPort;
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmEmailService confirmEmailService;

    @Override
    public BaseResponse register(UserRegisterRequest request) {
        try {
            request.validateSelf();
            User user = buildUser(request);
            tryRegisterUser(user);
            if(isEmailServiceEnable){
                confirmEmailService.send(user, request.getRedirectUrl());
            }
            return onRequestSuccess();
        }
        catch (Exception e){
            logger.debug(String.format("Failed to register user. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }

    @Override
    public BaseResponse<RedirectUrlResponse> confirmEmailCallback(String token) {
        try {
            ConfirmEmail confirmEmail = confirmEmailService.validate(token);
            updateUser(confirmEmail.getUser());
            return onRequestSuccess(new RedirectUrlResponse(confirmEmail.getRedirectLink()));
        }
        catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private User buildUser(UserRegisterRequest command){
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        Password password = new Password(encodedPassword, EncryptionAlgorithm.getDefaultAlgorithm());
        String encodedAnswer = passwordEncoder.encode(command.getRecoveryAnswer());
        Recovery recovery = new Recovery(command.getRecoveryQuestion(),encodedAnswer,EncryptionAlgorithm.getDefaultAlgorithm());
        return mapper.mapRegisterRequest(command, password, recovery, isEmailServiceEnable ? false : true);
    }
    private void tryRegisterUser(User user){
        if(userLoadPort.isExist(user)){
            throw new UserExistException(mapper.mapRegisterRequest(user));
        }
        userRegisterPort.register(user);
    }

    private void updateUser(User user){
        userUpdatePort.update(user.updateProfileEmailConfirmed(true).updateIsEnable(true));
    }
}
