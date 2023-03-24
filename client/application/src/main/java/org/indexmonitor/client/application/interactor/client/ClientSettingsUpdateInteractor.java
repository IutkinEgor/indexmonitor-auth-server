package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientExistException;
import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.mappers.ClientMapper;
import org.indexmonitor.client.application.ports.in.client.ClientSettingsUpdateUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateRequest;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientUpdatePort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientSettingsUpdateInteractor extends Interactor implements ClientSettingsUpdateUseCase {

    private final ClientLoadPort clientLoadPort;
    private final ClientUpdatePort clientUpdatePort;
    private final ClientMapper clientMapper;
    @Override
    public BaseResponse update(ClientUpdateRequest request) {
        try{
            request.validateSelf();
            Client client = tryLoadClient(request);
            tryUpdateClient(client, request);
            return onRequestSuccess();
        }
        catch (Exception e){
            return onRequestFailure(e);
        }
    }
    Client tryLoadClient(ClientUpdateRequest request){
        Optional<Client> client = clientLoadPort.findById(new BaseId(request.getId()));
        if(client.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return client.get();
    }

    void tryUpdateClient(Client client, ClientUpdateRequest request){
        Client newClient = clientMapper.mapUpdateRequest(client,request);
        if(clientLoadPort.isExistMoreThanOne(newClient)){
            throw new ClientExistException();
        }
        this.clientUpdatePort.update(newClient);
    }
}
