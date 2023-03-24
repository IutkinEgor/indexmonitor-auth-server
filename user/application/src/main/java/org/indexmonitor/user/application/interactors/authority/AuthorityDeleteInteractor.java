package org.indexmonitor.user.application.interactors.authority;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityNotFoundException;
import org.indexmonitor.user.application.exceptions.roleExceptions.RoleUsedByUserException;
import org.indexmonitor.user.application.ports.in.authority.AuthorityDeleteUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityDeleteRequest;
import org.indexmonitor.user.application.ports.out.authority.AuthorityDeletePort;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class AuthorityDeleteInteractor extends Interactor implements AuthorityDeleteUseCase {

    private final AuthorityLoadPort authorityLoadPort;
    private final AuthorityDeletePort authorityDeletePort;

    @Override
    public BaseResponse delete(AuthorityDeleteRequest request) {
        try {
            request.validateSelf();
            tryDelete(request);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    void tryDelete(AuthorityDeleteRequest request){
        if(!authorityLoadPort.isExistById(new BaseId(request.getId()))){
            throw new AuthorityNotFoundException();
        }
        if(authorityLoadPort.isAuthorityUsedByUser(request.getId())){
            throw new RoleUsedByUserException();
        }
        authorityDeletePort.delete(new BaseId(request.getId()));
    }
}
