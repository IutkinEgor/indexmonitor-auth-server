package org.indexmonitor.user.application.interactors.role;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleExistException;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleNotFoundException;
import org.indexmonitor.user.application.mappers.RoleMapper;
import org.indexmonitor.user.application.ports.in.role.RoleSettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsUpdateRequest;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.application.ports.out.role.RoleUpdatePort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class RoleUpdateInteractor extends Interactor implements RoleSettingsUpdateUseCase {

    private final RoleLoadPort roleLoadPort;
    private final RoleUpdatePort roleUpdatePort;
    private final RoleMapper roleMapper;

    @Override
    public BaseResponse update(RoleSettingsUpdateRequest request) {
        try {
            request.validateSelf();
            tryUpdateRole(request);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private void tryUpdateRole(RoleSettingsUpdateRequest request){
        Optional<Role> role = roleLoadPort.findById(BaseId.map(request.getId()));
        if(role.isEmpty()){
            throw new RoleNotFoundException();
        }
        var isSameName = role.get().getName().equals(request.getName());
        if(!isSameName && roleLoadPort.isExistByName(request.getName())){
            throw new RoleExistException();
        }
        roleUpdatePort.update(roleMapper.mapUpdateRequest(role.get(),request));
    }
}
