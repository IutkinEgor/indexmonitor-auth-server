package org.indexmonitor.user.application.interactors.role;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleExistException;
import org.indexmonitor.user.application.mappers.RoleMapper;
import org.indexmonitor.user.application.ports.in.role.RoleRegisterUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.application.ports.out.role.RoleRegisterPort;
import org.indexmonitor.user.domain.aggregates.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class RoleRegisterInteractor extends Interactor implements RoleRegisterUseCase {

    private final RoleRegisterPort roleRegisterPort;
    private final RoleLoadPort roleLoadPort;
    private final RoleMapper roleMapper;

    @Override
    public BaseResponse register(RoleRegisterRequest request) {
        try{
            request.validateSelf();
            Role role = buildRole(request);
            tryRegisterRole(role);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private Role buildRole(RoleRegisterRequest request){
        return roleMapper.mapRegisterRequest(request,roleRegisterPort.generateId());
    }

    private void tryRegisterRole(Role role){
        if(roleLoadPort.isExistByName(role.getName())){
            throw new RoleExistException();
        }
        roleRegisterPort.register(role);
    }
}
