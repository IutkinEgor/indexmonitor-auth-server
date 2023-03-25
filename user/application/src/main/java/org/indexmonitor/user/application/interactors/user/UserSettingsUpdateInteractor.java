package org.indexmonitor.user.application.interactors.user;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserExistException;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserSettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserSettingsUpdateRequest;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.application.ports.out.user.UserUpdatePort;
import org.indexmonitor.user.domain.aggregates.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class UserSettingsUpdateInteractor extends Interactor implements UserSettingsUpdateUseCase {

    private final UserLoadPort userLoadPort;
    private final UserUpdatePort userUpdatePort;

    @Override
    public BaseResponse update(UserSettingsUpdateRequest request) {
        try
        {
            request.validateSelf();
            tryUpdateUser(request, tryLoadUser(request));
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private User tryLoadUser(UserSettingsUpdateRequest request){
        Optional<User> user = userLoadPort.findByUserId(BaseId.map(request.getUserId()));
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    private void tryUpdateUser(UserSettingsUpdateRequest request, User user){
        if(!request.getUserName().equals(user.getUserName()) && userLoadPort.isExistByUserName(request.getUserName())){
            throw new UserExistException();
        }
        User updatedUser = user.userUpdateBuilder()
                .userName(request.getUserName())
                .isUserNonLocked(request.getIsUserNonLocked())
                .isUserNonExpired(request.getIsUserNonExpired())
                .isCredentialsNonExpired(request.getIsCredentialsNonExpired())
                .isEnabled(request.getIsEnabled())
                .build();
        userUpdatePort.update(updatedUser);
    }
}
