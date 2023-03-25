package org.indexmonitor.client.application.interactor.scope;

import org.indexmonitor.client.application.exceptions.scope.ScopeNotFoundException;
import org.indexmonitor.client.application.exceptions.scope.ScopeUsedByClientException;
import org.indexmonitor.client.application.mappers.ScopeMapper;
import org.indexmonitor.client.application.ports.in.scope.ScopeLoadUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopePageLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeLoadRequest;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopePageResponse;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeResponse;
import org.indexmonitor.client.application.ports.in.scope.responses.ScopeUsedByClientsResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ScopeLoadInteractor extends Interactor implements ScopeLoadUseCase {

    private final ClientLoadPort clientLoadPort;
    private final ScopeLoadPort scopeLoadPort;

    @Override
    public BaseResponse<Set<ScopeUsedByClientsResponse>> findAllScopeClients(ScopeLoadRequest request) {
        try {
            request.validateSelf();
            Set<ScopeUsedByClientsResponse> responses = tryLoadAllClient(request).stream().map(client ->
                new ScopeUsedByClientsResponse(client.getId().getValueAsString(), client.getClientId(), client.getName())
            ).collect(Collectors.toSet());
            return onRequestSuccess(responses);
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    @Override
    public BaseResponse<ScopeResponse> findById(ScopeLoadRequest request) {
        try {
            request.validateSelf();
            return new BaseResponse(ScopeResponse.map(tryLoadScope(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    Scope tryLoadScope(ScopeLoadRequest request){
        Optional<Scope> scope = scopeLoadPort.findById(new BaseId(request.getId()));
        if(scope.isEmpty()){
            throw new ScopeNotFoundException();
        }
        return scope.get();
    }

    Set<Client> tryLoadAllClient(ScopeLoadRequest request){
        if(!scopeLoadPort.isExistById(new BaseId(request.getId()))){
            throw new ScopeNotFoundException();
        }
        Set<Client> clients = clientLoadPort.findAllByScopeId(new BaseId(request.getId()));
        if(!clients.isEmpty()) {
            throw new ScopeUsedByClientException();
        }
        return clients;
    }
}
