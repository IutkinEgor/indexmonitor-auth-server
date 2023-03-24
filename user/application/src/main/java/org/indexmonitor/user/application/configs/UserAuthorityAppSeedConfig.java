package org.indexmonitor.user.application.configs;

import org.indexmonitor.user.application.ports.in.authority.AuthorityRegisterUseCase;
import org.indexmonitor.user.application.ports.in.authority.requests.AuthorityRegisterRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.beans.Transient;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAuthorityAppSeedConfig {

    @Value("${app.data.seed.authority.enable}")
    private Boolean seed_enable;
    @Value("#{'${app.data.seed.authority.set}'.split(',')}")
    private List<String> authorities;
    private final AuthorityRegisterUseCase authorityRegisterUseCase;

    @PostConstruct
    @Transient
    public void seed(){
        if(seed_enable && authorities != null && !authorities.isEmpty()) {
            authorities.forEach(authority -> authorityRegisterUseCase.register(buildAuthorityRegisterRequest(authority)));
        }
    }

    private AuthorityRegisterRequest buildAuthorityRegisterRequest(String authorityName){
        var authority = new AuthorityRegisterRequest();
        authority.setName(authorityName);
        authority.setDescription("Default authority");
        authority.setIsEnable(true);
        authority.setIsObtainable(true);
        return authority;
    }
}
