package org.indexmonitor.client.application.interactor.scope;

import org.indexmonitor.client.application.exceptions.client.ClientExistException;
import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.mappers.ScopeMapper;
import org.indexmonitor.client.application.ports.in.scope.ScopeUpdateUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeUpdateRequest;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.application.ports.out.scope.ScopeUpdatePort;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ScopeUpdateInteractor extends Interactor implements ScopeUpdateUseCase {

    private final ScopeUpdatePort scopeUpdatePort;
    private final ScopeLoadPort scopeLoadPort;
    private final ScopeMapper mapper;

    @Override
    public BaseResponse update(ScopeUpdateRequest request) {
        try{
            request.validateSelf();
            tryUpdateScope(tryLoadScope(request), request);
            return onRequestSuccess();
        }
        catch (Exception e){
            return onRequestFailure(e);
        }
    }
    Scope tryLoadScope(ScopeUpdateRequest request){
        Optional<Scope> scope = scopeLoadPort.findById(new BaseId(request.getId()));
        if(scope.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return scope.get();
    }

    void tryUpdateScope(Scope scope, ScopeUpdateRequest request){
        Scope newScope = mapper.mapUpdateRequest(scope,request);
        if(scopeLoadPort.isExistMoreThanOne(newScope)){
            throw new ClientExistException();
        }
        this.scopeUpdatePort.update(newScope);
    }
}
