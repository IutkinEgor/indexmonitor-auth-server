package org.indexmonitor.user.application.interactors.role;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleNotFoundException;
import org.indexmonitor.user.application.ports.in.role.RoleSettingsLoadUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.role.responses.RoleResponse;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class RoleSettingsLoadInteractor extends Interactor implements RoleSettingsLoadUseCase {

    private final RoleLoadPort roleLoadPort;

    @Override
    public BaseResponse<RoleResponse> load(RoleSettingsLoadRequest request) {
        try {
            request.validateSelf();
            return onRequestSuccess(RoleResponse.map(tryLoadRole(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }
    Role tryLoadRole(RoleSettingsLoadRequest request){
        Optional<Role> role = roleLoadPort.findById(BaseId.map(request.getId()));
        if(role.isEmpty()){
            throw new RoleNotFoundException();
        }
        return role.get();
    }

}
