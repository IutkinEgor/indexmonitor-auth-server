package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.ports.in.client.ClientDeleteUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientDeleteRequest;
import org.indexmonitor.client.application.ports.out.client.ClientDeletePort;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ClientDeleteInteractor extends Interactor implements ClientDeleteUseCase {
    private final Logger logger = LoggerFactory.getLogger(ClientDeleteInteractor.class);
    private final ClientLoadPort clientLoadPort;
    private final ClientDeletePort clientDeletePort;

    @Override
    public BaseResponse deleteById(ClientDeleteRequest request) {
        try {
            request.validateSelf();
            tryDelete(request);
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to delete client with ID '%s'. Exception message: '%s'.",  request.getId() == null ? "null" : request.getId(), e.getMessage()));
            return onRequestFailure(e);
        }
    }

    void tryDelete(ClientDeleteRequest request){
        if(!clientLoadPort.isExistById(new BaseId(request.getId()))){
            throw new ClientNotFoundException();
        }
        clientDeletePort.delete(request.getId());
    }
}
