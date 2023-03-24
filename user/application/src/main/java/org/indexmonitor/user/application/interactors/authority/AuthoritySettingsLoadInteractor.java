package org.indexmonitor.user.application.interactors.authority;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import org.indexmonitor.user.application.exceptions.authority.AuthorityNotFoundException;
import org.indexmonitor.user.application.ports.in.authority.AuthoritySettingsLoadUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthoritySettingsLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityResponse;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class AuthoritySettingsLoadInteractor extends Interactor implements AuthoritySettingsLoadUseCase {

    private final AuthorityLoadPort authorityLoadPort;

    @Override
    public BaseResponse<AuthorityResponse> load(AuthoritySettingsLoadRequest request) {
        try {
            request.validateSelf();
            return onRequestSuccess(AuthorityResponse.map(tryLoadAuthority(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }
    Authority tryLoadAuthority(AuthoritySettingsLoadRequest request){
        Optional<Authority> authority = authorityLoadPort.findById(new BaseId(request.getId()));
        if(authority.isEmpty()){
            throw new AuthorityNotFoundException();
        }
        return authority.get();
    }
}
