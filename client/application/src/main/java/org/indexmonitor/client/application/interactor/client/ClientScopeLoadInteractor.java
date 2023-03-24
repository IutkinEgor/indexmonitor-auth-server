package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.ports.in.client.ClientScopeLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientScopesPageResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class ClientScopeLoadInteractor extends Interactor implements ClientScopeLoadUseCase {

    private final ClientLoadPort clientLoadPort;

    @Override
    public BaseResponse<Set<ClientScopesPageResponse>> findById(ClientLoadRequest request) {
        try{
            request.validateSelf();
            checkClientExist(request);
            return onRequestSuccess(tryLoadScope(request).stream().map(scope ->
                    new ClientScopesPageResponse(scope.getId().getValue().toString(), scope.getName())).collect(Collectors.toSet()));
        }
        catch (Exception e){
            return onRequestFailure(e);
        }
    }

    private void checkClientExist(ClientLoadRequest request){
        if(!clientLoadPort.isExistById(new BaseId(request.getId()))) {
            throw new ClientNotFoundException();
        }
    }

    private Set<Scope> tryLoadScope(ClientLoadRequest request){
        return clientLoadPort.findScopes(new BaseId(request.getId())).get();
    }
}
