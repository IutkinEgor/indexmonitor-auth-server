package org.indexmonitor.user.application.interactors.user;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserExistException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserProfileUpdateUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserProfileUpdateRequest;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class UserProfileUpdateInteractor extends Interactor implements UserProfileUpdateUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserProfileUpdateInteractor.class);
    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;

    @Override
    public BaseResponse update(UserProfileUpdateRequest request) {
        try
        {
            request.validateSelf();
            tryUpdateUser(request, tryLoadUser(request));
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to update profile for user with ID '%s'. Exception message: '%s'.",  request.getId() == null ? "null" : request.getId(), e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private User tryLoadUser(UserProfileUpdateRequest request){
        Optional<User> user = userLoadPort.findByUserId(BaseId.map(request.getId()));
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    private void tryUpdateUser(UserProfileUpdateRequest request, User user){
        if(!request.getEmail().equals(user.getProfile().getEmail()) && userLoadPort.isExistByEmail(request.getEmail())){
            throw new UserExistException();
        }
        User updatedUser = user.userProfileUpdateBuilder()
                .givenName(request.getGivenName())
                .familyName(request.getFamilyName())
                .email(request.getEmail())
                .isEmailConfirmed(request.getEmailConfirmed())
                .build();
        userUpdatePort.update(updatedUser);
    }

}
