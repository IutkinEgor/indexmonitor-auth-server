package org.indexmonitor.user.application.configs;

import org.indexmonitor.user.application.ports.in.role.RoleRegisterUseCase;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRoleAppSeedConfig {
    @Value("${app.data.seed.role.enable}")
    private Boolean seed_enable;
    @Value("#{'${app.data.seed.role.set}'.split(',')}")
    private List<String> roles;
    private final RoleRegisterUseCase roleRegisterUseCase;

    @PostConstruct
    @Transient
    public void seed(){
        if(seed_enable && roles != null && !roles.isEmpty()) {
            roles.forEach(role -> roleRegisterUseCase.register(buildRoleRegisterRequest(role)));
        }
    }

    private RoleRegisterRequest buildRoleRegisterRequest(String roleName){
        var role = new RoleRegisterRequest();
        role.setName(roleName);
        role.setDescription("Default roleName");
        role.setIsEnable(true);
        role.setIsObtainable(true);
        return role;
    }
}
