package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.userExceptions.UserNotFoundException;
import org.indexmonitor.user.application.ports.in.user.UserProfileLoadUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserProfileResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
class UserProfileLoadInteractor extends Interactor implements UserProfileLoadUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserProfileLoadInteractor.class);
    private final UserLoadPort userLoadPort;
    @Override
    public BaseResponse<UserProfileResponse> load(UserLoadRequest request) {
        try{
            request.validateSelf();
            return onRequestSuccess(UserProfileResponse.map(tryLoadUser(request)));
        }catch (Exception e){
            logger.debug(String.format("Failed to load profile for user with ID '%s'. Exception message: '%s'.",  request.getId() == null ? "null" : request.getId(), e.getMessage()));
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
