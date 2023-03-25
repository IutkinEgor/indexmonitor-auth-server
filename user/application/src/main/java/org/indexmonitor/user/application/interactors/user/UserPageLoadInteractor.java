package org.indexmonitor.user.application.interactors.user;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.user.UserPageLoadUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserPageLoadRequest;
import org.indexmonitor.user.application.ports.in.user.responses.UserPageResponse;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserPageLoadInteractor extends Interactor implements UserPageLoadUseCase {
    private final Logger logger = LoggerFactory.getLogger(UserPageLoadInteractor.class);
    private final UserLoadPort userLoadPort;
    @Override
    public BasePageResponse<UserPageResponse> load(UserPageLoadRequest request) {
        try{
            request.validateSelf();
            return onPageRequestSuccess(UserPageResponse.map(tryLoadUsers(request)));
        }catch (Exception e){
            logger.debug(String.format("Failed to load user page. Exception message: '%s'.", e.getMessage()));
            return onPageRequestFailure(e);
        }
    }

    private BasePage<User> tryLoadUsers(UserPageLoadRequest request){
        return userLoadPort.findAll(request.getOffset(),request.getLimit());
    }
}
