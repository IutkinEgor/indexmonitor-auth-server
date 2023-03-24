package org.indexmonitor.user.application.interactors.role;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.role.RolePageLoadUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RolePageLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RolePageResponse;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class RolePageLoadInteractor extends Interactor implements RolePageLoadUseCase {

    private final RoleLoadPort roleLoadPort;

    @Override
    public BasePageResponse<RolePageResponse> load(RolePageLoadRequest request) {
        try {
            request.validateSelf();
            return onPageRequestSuccess(RolePageResponse.map(tryLoadPage(request)));
        }catch (Exception e){
            return onPageRequestFailure(e);
        }
    }

    BasePage<Role> tryLoadPage(RolePageLoadRequest request){
        return roleLoadPort.findAll(request.getPage(),request.getSize());
    }
}
