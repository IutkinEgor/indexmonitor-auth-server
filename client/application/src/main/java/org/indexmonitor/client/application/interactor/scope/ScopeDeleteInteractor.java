package org.indexmonitor.client.application.interactor.scope;

import org.indexmonitor.client.application.exceptions.scope.ScopeNotFoundException;
import org.indexmonitor.client.application.exceptions.scope.ScopeUsedByClientException;
import org.indexmonitor.client.application.ports.in.scope.ScopeDeleteUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeDeleteRequest;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.scope.ScopeDeletePort;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ScopeDeleteInteractor extends Interactor implements ScopeDeleteUseCase {
    private final Logger logger = LoggerFactory.getLogger(ScopeDeleteInteractor.class);
    ClientLoadPort clientLoadPort;
    ScopeDeletePort scopeDeletePort;
    ScopeLoadPort scopeLoadPort;
    @Override
    public BaseResponse delete(ScopeDeleteRequest request) {
        try{
            request.validateSelf();
            tryDelete(request);
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to delete scope with ID '%s'. Exception message: '%s'.", request.getId() == null ? "null" : request.getId(), e.getMessage()));
            return onRequestFailure(e);
        }
    }

    void tryDelete(ScopeDeleteRequest request){
        if(!scopeLoadPort.isExistById(new BaseId(request.getId()))){
            throw new ScopeNotFoundException();
        }
       Set<Client> clients = clientLoadPort.findAllByScopeId(new BaseId(request.getId()));
       if(!clients.isEmpty()) {
           throw new ScopeUsedByClientException();
       }
        scopeDeletePort.delete(new BaseId(request.getId()));
    }
}
