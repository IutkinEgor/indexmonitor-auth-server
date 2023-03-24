package org.indexmonitor.user.application.interactors.authority;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityExistException;
import org.indexmonitor.user.application.exceptions.authority.AuthorityNotFoundException;
import org.indexmonitor.user.application.mappers.AuthorityMapper;
import org.indexmonitor.user.application.ports.in.authority.AuthoritySettingsUpdateUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsUpdateRequest;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.application.ports.out.authority.AuthorityUpdatePort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class AuthoritySettingsUpdateInteractor extends Interactor implements AuthoritySettingsUpdateUseCase {

    private final AuthorityLoadPort authorityLoadPort;
    private final AuthorityUpdatePort authorityUpdatePort;
    private final AuthorityMapper authorityMapper;

    @Override
    public BaseResponse update(AuthoritySettingsUpdateRequest request) {
        try {
            request.validateSelf();
            tryUpdateAuthority(request);
            return onRequestSuccess();
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private void tryUpdateAuthority(AuthoritySettingsUpdateRequest request){
        Optional<Authority> authority = authorityLoadPort.findById(BaseId.map(request.getId()));
        if(authority.isEmpty()){
            throw new AuthorityNotFoundException();
        }
        var isSameName = authority.get().getName().equals(request.getName());
        if(!isSameName && authorityLoadPort.isExistByName(request.getName())){
            throw new AuthorityExistException();
        }
        authorityUpdatePort.update(authorityMapper.mapUpdateRequest(authority.get(),request));
    }
}
