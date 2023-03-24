package org.indexmonitor.user.application.interactors.role;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleNotFoundException;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleUsedByUserException;
import org.indexmonitor.user.application.ports.in.role.RoleDeleteUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleDeleteRequest;
import org.indexmonitor.user.application.ports.out.role.RoleDeletePort;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class RoleDeleteInteractor extends Interactor implements RoleDeleteUseCase {

    private final RoleLoadPort roleLoadPort;
    private final RoleDeletePort roleDeletePort;

    @Override
    public BaseResponse delete(RoleDeleteRequest request) {
        try {
            request.validateSelf();
            tryDelete(request);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    void tryDelete(RoleDeleteRequest request){
        if(!roleLoadPort.isExistById(BaseId.map(request.getId()))){
            throw new RoleNotFoundException();
        }
        if(roleLoadPort.isRoleUsedByUser(request.getId())){
            throw new RoleUsedByUserException();
        }
        roleDeletePort.delete(BaseId.map(request.getId()));
    }
}
