package org.indexmonitor.client.application.interactor.scope;

import org.indexmonitor.client.application.exceptions.scope.ScopeExistException;
import org.indexmonitor.client.application.mappers.ScopeMapper;
import org.indexmonitor.client.application.ports.in.scope.ScopeRegisterUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.application.ports.out.scope.ScopeRegisterPort;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
class ScopeRegisterInteractor extends Interactor implements ScopeRegisterUseCase {
    private final Logger logger = LoggerFactory.getLogger(ScopeRegisterInteractor.class);
    private final ScopeRegisterPort scopeRegisterPort;
    private final ScopeLoadPort scopeLoadPort;
    private final ScopeMapper mapper;

    @Override
    public BaseResponse register(ScopeRegisterRequest request) {
        try{
            request.validateSelf();
            tryRegisterScope(buildDefaultScope(request));
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to register scope. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private Scope buildDefaultScope(ScopeRegisterRequest request){
        return mapper.mapRegisterRequest(request, getNewScopeId());
    }

    private BaseId getNewScopeId(){
        return new BaseId<UUID>(UUID.randomUUID());
    }

    private void tryRegisterScope(Scope scope){
        if(scopeLoadPort.isExist(scope)){
            throw new ScopeExistException();
        }
        scopeRegisterPort.register(scope);
    }
}
