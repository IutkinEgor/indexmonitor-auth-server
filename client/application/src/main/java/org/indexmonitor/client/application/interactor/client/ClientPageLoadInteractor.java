package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.ports.in.client.ClientPageLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadPageRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientPageModelResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
class ClientPageLoadInteractor extends Interactor implements ClientPageLoadUseCase {
    private final ClientLoadPort clientLoadPort;

    @Override
    public BaseResponse<Set<ClientPageModelResponse>> load(ClientLoadPageRequest request) {
        try {
            request.validateSelf();
            return onRequestSuccess(ClientPageModelResponse.map(tryLoadClientPage(request)));
        }catch (Exception e){
            return onRequestFailure(e);
        }
    }

    Set<Client> tryLoadClientPage(ClientLoadPageRequest request){
        Set<Client> clients = clientLoadPort.findAll(request.getOffset(), request.getLimit());
        return clients;
    }
}
