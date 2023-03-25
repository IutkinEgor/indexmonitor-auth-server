package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientNotFoundException;
import org.indexmonitor.client.application.ports.in.client.ClientTokenSettingsUpdateUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientUpdateTokenSettingsRequest;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientUpdatePort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.valueObjects.ClientTokenSettings;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ClientTokenSettingsUpdateInteractor extends Interactor implements ClientTokenSettingsUpdateUseCase {
    private final Logger logger = LoggerFactory.getLogger(ClientTokenSettingsUpdateInteractor.class);
    private final ClientLoadPort clientLoadPort;
    private final ClientUpdatePort clientUpdatePort;

    @Override
    public BaseResponse update(ClientUpdateTokenSettingsRequest request) {
        try{
            request.validateSelf();
            Client client = tryLoadClient(request);
            tryUpdateTokenSettings(client, request);
            return onRequestSuccess();
        }
        catch (Exception e){
            logger.debug(String.format("Failed to update token settings for client with ID '%s'. Exception message: '%s'.",  request.getId() == null ? "null" : request.getId(), e.getMessage()));
            return onRequestFailure(e);
        }
    }
    Client tryLoadClient(ClientUpdateTokenSettingsRequest request){
        Optional<Client> client = clientLoadPort.findById(new BaseId(request.getId()));
        if(client.isEmpty()) {
            throw new ClientNotFoundException();
        }
        return client.get();
    }

    void tryUpdateTokenSettings(Client client, ClientUpdateTokenSettingsRequest request){
        ClientTokenSettings tokenSettings = ClientTokenSettings.builder()
                .authorizationCodeTimeToLive(request.getAuthorizationCodeTimeToLive())
                .accessTokenTimeToLive(request.getAccessTokenTimeToLive())
                .refreshTokenTimeToLive(request.getRefreshTokenTimeToLive())
                .reuseRefreshTokens(request.isReuseRefreshTokens())
                .tokenSignatureAlgorithm(client.getTokenSettings().getTokenSignatureAlgorithm())
                .build();

        this.clientUpdatePort.update(client.updateTokenSettings(tokenSettings));
    }
}
