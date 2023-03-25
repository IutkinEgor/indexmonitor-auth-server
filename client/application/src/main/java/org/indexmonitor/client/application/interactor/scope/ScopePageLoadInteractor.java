package org.indexmonitor.client.application.interactor.scope;

import lombok.AllArgsConstructor;
import org.indexmonitor.client.application.ports.in.scope.ScopePageLoadUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopePageLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScopePageLoadInteractor extends Interactor implements ScopePageLoadUseCase {
    private final ScopeLoadPort scopeLoadPort;

    @Override
    public BasePageResponse<ScopePageResponse> load(ScopePageLoadRequest request) {
        try {
            request.validateSelf();
            return onPageRequestSuccess(ScopePageResponse.map(tryLoadPage(request)));
        }catch (Exception e){
            return onPageRequestFailure(e);
        }
    }

    BasePage<Scope> tryLoadPage(ScopePageLoadRequest request){
        return scopeLoadPort.findAll(request.getPage(),request.getSize());
    }
}
