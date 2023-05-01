package org.indexmonitor.user.application.interactors.user;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserPasswordResetUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetStartRequest;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import org.indexmonitor.user.application.ports.in.user.responses.UserPasswordResetResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.application.services.UserPasswordResetService;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class UserPasswordResetInteractor extends Interactor implements UserPasswordResetUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserPasswordResetInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final PasswordEncoder passwordEncoder;
    private final UserPasswordResetService userPasswordResetService;
    @Override
    public BaseResponse<UserPasswordResetResponse> loadUserInfo(UserPasswordResetStartRequest request) {
        try {
            request.validateSelf();
            User user = tryRetrieveUser(request.getEmail());
            return onRequestSuccess(UserPasswordResetResponse.map(user));
        }catch (Exception e){
            logger.debug(String.format("Failed to load user. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }
    @Override
    public BaseResponse sendToken(UserPasswordResetRequest request) {
        try{
            request.validateSelf();
            User user = tryRetrieveUser(request.getEmail());
            userPasswordResetService.validateAnswerAndSendRecoveryLink(request,user);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }
    @Override
    public BaseResponse validateToken(String token) {
        try{
            BaseId userId = userPasswordResetService.validateCallbackAndReturnUserId(token);
            return onRequestSuccess(userId);
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    @Override
    public BaseResponse<RedirectUrlResponse> updatePassword(UserPasswordResetCallbackRequest request) {
        try {
            request.validateSelf();
            tryUpdateUserPassword(request);
            return onRequestSuccess(new RedirectUrlResponse(userPasswordResetService.removeTokenAndReturnRedirectLink(BaseId.map(request.getUserId()))));
        }catch (Exception e) {
            return onRequestFailure(e);
        }
    }
    private User tryRetrieveUser(String email){
        Optional<User> user = userLoadPort.findByEmail(email);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }
    private void tryUpdateUserPassword(UserPasswordResetCallbackRequest request){
        Optional<User> user = userLoadPort.findByUserId(BaseId.map(request.getUserId()));
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Password password = new Password(encodedPassword, EncryptionAlgorithm.getDefaultAlgorithm());
        User updatedUser = user.get().updatePassword(password);
        userUpdatePort.update(updatedUser);
    }
}
