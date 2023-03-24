package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.exceptions.scope.ScopeNotFoundException;
import org.indexmonitor.client.application.ports.in.client.ClientScopeAddUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateScopeRequest;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientUpdatePort;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
class ClientScopeAddInteractor extends Interactor implements ClientScopeAddUseCase {

    private final ClientLoadPort clientLoadPort;
    private final ClientUpdatePort clientUpdatePort;
    private final ScopeLoadPort scopeLoadPort;

    @Override
    public BaseResponse add(ClientUpdateScopeRequest request) {
        try{
            request.validateSelf();
            Set<Scope> scopes = tryLoadRequestedScopes(request);
            Client client = tryLoadClient(request);
            tryAddClientScope(client, scopes);
            return  onRequestSuccess();
        }
        catch (Exception e){
            return onRequestFailure(e);
        }
    }
    private Set<Scope> tryLoadRequestedScopes(ClientUpdateScopeRequest request){
        Set<Scope> scopes= new HashSet<>();
        request.getScopeIds().forEach(requestedScope -> {
            Optional<Scope> scope = scopeLoadPort.findById(new BaseId(requestedScope));
            if(scope.isEmpty()){
                throw new ScopeNotFoundException();
            }
            scopes.add(scope.get());
        });
        return scopes;
    }

    private Client tryLoadClient(ClientUpdateScopeRequest request){
        Optional<Client> client = clientLoadPort.findById(new BaseId(request.getId()));
        if(client.isEmpty()){
            throw new ClientNotFoundException();
        }
        return client.get();
    }

    private void tryAddClientScope(Client client, Set<Scope> newScopes){
        Set<Scope> scopes = client.getScopes();
        scopes.addAll(newScopes);
        client.updateScopes(scopes);
        clientUpdatePort.update(client);
    }
}
