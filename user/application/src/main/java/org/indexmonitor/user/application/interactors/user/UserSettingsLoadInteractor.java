package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserSettingsLoadUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserSettingsResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
class UserSettingsLoadInteractor extends Interactor implements UserSettingsLoadUseCase {
    private final UserLoadPort userLoadPort;
    @Override
    public BaseResponse<UserSettingsResponse> load(UserLoadRequest request) {
        try
        {
            request.validateSelf();
            return onRequestSuccess(UserSettingsResponse.map(tryLoadUser(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private User tryLoadUser(UserLoadRequest request){
       Optional<User> user = userLoadPort.findByUserId(BaseId.map(request.getId()));
       if(user.isEmpty()){
           throw new UserNotFoundException();
       }
       return user.get();
    }
}
