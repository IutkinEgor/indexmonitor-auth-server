package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.ports.in.client.ClientSettingsLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientSettingsResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ClientSettingsLoadInteractor extends Interactor implements ClientSettingsLoadUseCase {

    private final ClientLoadPort clientLoadPort;

    @Override
    public BaseResponse<ClientSettingsResponse> load(ClientLoadRequest request) {
        try {
            request.validateSelf();
            return new BaseResponse(ClientSettingsResponse.map(tryLoadClient(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    Client tryLoadClient(ClientLoadRequest request){
        Optional<Client> client = clientLoadPort.findById(new BaseId(request.getId()));
        if(client.isEmpty()){
            throw new ClientNotFoundException();
        }
        return client.get();
    }

}
