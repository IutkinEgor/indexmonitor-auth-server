package org.indexmonitor.client.application.configs;

import org.indexmonitor.client.application.ports.in.scope.ScopeRegisterUseCase;
import org.indexmonitor.client.application.ports.in.scope.requests.ScopeRegisterRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScopeAppSeedConfig {

    @Value("${app.data.seed.scope.enable}")
    private Boolean seed_enable;
    @Value("#{'${app.data.seed.scope.set}'.split(',')}")
    private List<String> scopes;
    private final ScopeRegisterUseCase scopeRegisterUseCase;

    @Transient
    @PostConstruct
    public void seed(){
        if(seed_enable){
            scopes.forEach(scope->{
                ScopeRegisterRequest request = new ScopeRegisterRequest();
                request.setName(scope);
                request.setIsEnable(true);
                request.setIsObtainable(true);
                scopeRegisterUseCase.register(request);
            });
        }
    }
}
