package org.indexmonitor.client.application.interactor.client;

import org.indexmonitor.client.application.exceptions.client.ClientExistException;
import org.indexmonitor.client.application.exceptions.scope.ScopeNotFoundException;
import org.indexmonitor.client.application.mappers.ClientMapper;
import org.indexmonitor.client.application.ports.in.client.ClientRegisterUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import org.indexmonitor.client.application.ports.out.client.ClientLoadPort;
import org.indexmonitor.client.application.ports.out.client.ClientRegisterPort;
import org.indexmonitor.client.application.ports.out.scope.ScopeLoadPort;
import org.indexmonitor.client.domain.aggregates.Client;
import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.client.domain.enums.AuthenticationMethod;
import org.indexmonitor.client.domain.valueObjects.ClientSecret;
import org.indexmonitor.common.application.models.Interactor;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BaseResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
@AllArgsConstructor
class ClientRegisterInteractor extends Interactor implements ClientRegisterUseCase {
    private final Logger logger = LoggerFactory.getLogger(ClientRegisterInteractor.class);
    private final ClientRegisterPort clientRegisterPort;
    private final ClientLoadPort clientLoadPort;
    private final ScopeLoadPort scopeLoadPort;
    private final ClientMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse register(ClientRegisterRequest request) {
        try{
            request.validateSelf();
            Client user = buildDefaultClient(request);
            tryRegisterClient(user);
            return onRequestSuccess();
        }catch (Exception e){
            logger.debug(String.format("Failed to register client. Exception message: '%s'.", e.getMessage()));
            return onRequestFailure(e);
        }
    }

    private Client buildDefaultClient(ClientRegisterRequest request){
        ClientSecret secret = null;
        if(request.getAuthenticationMethods().contains(AuthenticationMethod.CLIENT_SECRET_BASIC.getMethod().toUpperCase())) {
            String encodedSecrete = passwordEncoder.encode(request.getSecret());
            secret = new ClientSecret(encodedSecrete, EncryptionAlgorithm.getDefaultAlgorithm());
        }
        return mapper.mapRegisterRequest(request, getNewClientId(), secret, tryLoadRequestedScopes(request));
    }
    private BaseId getNewClientId(){
        return new BaseId<UUID>(UUID.randomUUID());
    }

    private Set<Scope> tryLoadRequestedScopes(ClientRegisterRequest request){
        Set<Scope> scopeSet = new HashSet<>();
        if(request.getScopes() == null || request.getScopes().isEmpty()) return scopeSet;
        request.getScopes().forEach(scope -> {
            Optional<Scope> scopeModel = scopeLoadPort.findByName(scope.toLowerCase());
            if(scopeModel.isEmpty()) {
                throw new ScopeNotFoundException();
            }
            scopeSet.add(scopeModel.get());
        });
        return scopeSet;
    }
    private void tryRegisterClient(Client user){
        if(clientLoadPort.isExist(user)){
            throw new ClientExistException();
        }
        clientRegisterPort.register(user);
    }


}
