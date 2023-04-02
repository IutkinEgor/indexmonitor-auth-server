package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.ports.in.client.ClientPageLoadUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientLoadPageRequest;
import org.indexmonitor.client.application.ports.in.client.responses.ClientPageModelResponse;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.common.domain.valueObjects.BasePageResponse;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
class ClientPageLoadInteractor extends Interactor implements ClientPageLoadUseCase {
    private final Logger logger = LoggerFactory.getLogger(ClientPageLoadInteractor.class);
    private final ClientLoadPort clientLoadPort;

    @Override
    public BasePageResponse<ClientPageModelResponse> load(ClientLoadPageRequest request) {
        try {
            request.validateSelf();
            return onPageRequestSuccess(ClientPageModelResponse.map(tryLoadClientPage(request)));
        }catch (Exception e){
            logger.debug(String.format("Failed to load client page. Exception message: '%s'.", e.getMessage()));
            return onPageRequestFailure(e);
        }
    }

    BasePage<Client> tryLoadClientPage(ClientLoadPageRequest request){
        return clientLoadPort.findAll(request.getOffset(), request.getLimit());
    }
}
