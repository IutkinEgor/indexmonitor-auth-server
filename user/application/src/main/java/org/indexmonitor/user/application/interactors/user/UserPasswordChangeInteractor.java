package org.indexmonitor.user.application.interactors.user;

import lombok.RequiredArgsConstructor;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserCredentialsException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserPasswordChangeUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoginRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordChangeCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordChangeRequest;
import org.indexmonitor.user.application.ports.in.user.requests.UserPasswordResetCallbackRequest;
import org.indexmonitor.user.application.ports.in.user.responses.RedirectUrlResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.application.services.UserPasswordChangeService;
import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPasswordChangeInteractor extends Interactor implements UserPasswordChangeUseCase {

    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;
    private final UserPasswordChangeService userPasswordChangeService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public BaseResponse verifyCredentials(UserPasswordChangeRequest request) {
        try {
            request.validateSelf();
            userPasswordChangeService.sendRecoveryLink(request, tryRetrieveUser(request));
           return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    @Override
    public BaseResponse<BaseId> validateToken(String token) {
        try {
            BaseId userId = userPasswordChangeService.validateCallbackAndReturnUserId(token);
            return onRequestSuccess(userId);
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    @Override
    public BaseResponse updatePassword(UserPasswordChangeCallbackRequest request) {
        try {
            request.validateSelf();
            tryUpdateUserPassword(request);
            return onRequestSuccess(new RedirectUrlResponse(userPasswordChangeService.removeTokenAndReturnRedirectLink(BaseId.map(request.getUserId()))));
        }catch (Exception e) {
            return onRequestFailure(e);
        }
    }

    private User tryRetrieveUser(UserPasswordChangeRequest request){
        Optional<User> user = userLoadPort.findByEmail(request.getEmail());
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        if(!passwordEncoder.matches(request.getPassword(),user.get().getPassword().getHash())){
            throw new UserCredentialsException();
        }
        return user.get();
    }

    private void tryUpdateUserPassword(UserPasswordChangeCallbackRequest request){
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
