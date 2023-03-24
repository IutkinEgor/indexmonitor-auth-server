package org.indexmonitor.client.application.configs;

import org.indexmonitor.client.application.ports.in.client.ClientRegisterUseCase;
import org.indexmonitor.client.application.ports.in.client.requests.ClientRegisterRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@DependsOn({"scopeAppSeedConfig"})
public class ClientAppSeedConfig {

    @Value("${app.data.seed.client.enable}")
    private Boolean seed_enable;
    @Value("${app.data.seed.client.clientId}")
    private String clientId;
    @Value("${app.data.seed.client.name}")
    private String name;
    @Value("${app.data.seed.client.description}")
    private String description;
    @Value("${app.data.seed.client.authenticationMethod}")
    private String authenticationMethod;
    @Value("${app.data.seed.client.grantType}")
    private String grantType;
    @Value("${app.data.seed.client.origin}")
    private String origin;
    @Value("${app.data.seed.client.redirectUris}")
    private String redirectUris;
    @Value("#{'${app.data.seed.client.scopes}'.split(',')}")
    private List<String> scopes;

    private final ClientRegisterUseCase clientRegisterUseCase;

    @Transient
    @PostConstruct
    public void seed() {
        if(seed_enable){
            ClientRegisterRequest request = new ClientRegisterRequest();
            request.setClientId(clientId);
            request.setName(name);
            request.setDescription(description);
            request.setAuthenticationMethods(Set.of(authenticationMethod));
            request.setAuthorizationGrantTypes(Set.of(grantType));
            request.setOrigin(origin);
            request.setRedirectUris(Set.of(redirectUris));
            request.setScopes(scopes.stream().collect(Collectors.toSet()));
            clientRegisterUseCase.register(request);
        }
    }
}
