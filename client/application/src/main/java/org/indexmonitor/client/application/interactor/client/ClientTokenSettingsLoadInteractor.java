package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.mappers.ClientMapper;
import org.indexmonitor.client.application.ports.in.client.ClientTokenSettingsLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientTokenSettingsResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class ClientTokenSettingsLoadInteractor extends Interactor implements ClientTokenSettingsLoadUseCase {

    private final ClientLoadPort clientLoadPort;
    private final ClientMapper mapper;

    @Override
    public BaseResponse<ClientTokenSettingsResponse> findById(ClientLoadRequest request) {
        try {
            request.validateSelf();
            return new BaseResponse(ClientTokenSettingsResponse.map(tryLoadClient(request)));
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
