package org.indexmonitor.user.application.interactors.authority;

import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.user.application.ports.in.authority.AuthorityPageLoadUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityPageLoadRequest;
import org.indexmonitor.user.application.ports.in.authority.responses.AuthorityPageResponse;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class AuthorityPageLoadInteractor extends Interactor implements AuthorityPageLoadUseCase {

    private final AuthorityLoadPort roleLoadPort;

    @Override
    public BasePageResponse<AuthorityPageResponse> load(AuthorityPageLoadRequest request) {
        try {
            request.validateSelf();
            return onPageRequestSuccess(AuthorityPageResponse.map(tryLoadPage(request)));
        }catch (Exception e){
            return onPageRequestFailure(e);
        }
    }

    BasePage<Authority> tryLoadPage(AuthorityPageLoadRequest request){
        return roleLoadPort.findAll(request.getPage(),request.getSize());
    }
}
