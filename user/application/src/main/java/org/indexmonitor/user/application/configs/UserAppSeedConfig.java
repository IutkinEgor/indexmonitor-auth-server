package org.indexmonitor.user.application.configs;

import org.indexmonitor.common.domain.exceptions.AdapterException;
import org.indexmonitor.user.application.ports.in.user.UserRegisterManualUseCase;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterManualRequest;
import org.indexmonitor.user.application.ports.out.authority.AuthorityLoadPort;
import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
import org.indexmonitor.user.domain.aggregates.Authority;
import org.indexmonitor.user.domain.aggregates.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.beans.Transient;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@DependsOn(value = {"userRoleAppSeedConfig", "userAuthorityAppSeedConfig"})
@RequiredArgsConstructor
public class UserAppSeedConfig {
    @Value("${app.data.seed.user.enable}")
    private Boolean seed_enable;
    @Value("${app.data.seed.user.username}")
    private String username;
    @Value("${app.data.seed.user.password}")
    private String password;
    @Value("${app.data.seed.user.givenName}")
    private String givenName;
    @Value("${app.data.seed.user.familyName}")
    private String familyName;
    @Value("${app.data.seed.user.email}")
    private String email;
    @Value("${app.data.seed.user.recovery.question}")
    private String question;
    @Value("#{'${app.data.seed.role.set}'.split(',')}")
    private Set<String> roles;
    @Value("#{'${app.data.seed.authority.set}'.split(',')}")
    private Set<String> authorities;
    private final UserRegisterManualUseCase userRegisterManualUseCase;
    private final RoleLoadPort roleLoadPort;
    private final AuthorityLoadPort authorityLoadPort;


    @Transient
    @PostConstruct
    public void seed(){
        if(seed_enable){
            try {
                registerUser();
            }catch (Exception e){
                if(e instanceof AdapterException){
                    System.out.println("User seed exception: " + e.getMessage());
                }
            }
        }
    }

    private void registerUser(){
        UserRegisterManualRequest request = new UserRegisterManualRequest();
        request.setUserName(username);
        request.setGivenName(givenName);
        request.setFamilyName(familyName);
        request.setEmail(email);
        request.setPassword(password);
        request.setConfirmPassword(password);
        request.setRoleIds(getSeededRoleIds());
        request.setAuthorityIds(getSeededAuthorityIds());
        userRegisterManualUseCase.register(request);
    }
    private Set<String> getSeededRoleIds(){
        Set<Role> loadedRoles = roleLoadPort.findAllByName(roles.stream().map(String::toUpperCase).collect(Collectors.toSet()));
        return loadedRoles.stream().map(role -> role.getId().getValueAsString()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<String> getSeededAuthorityIds(){
        Set<Authority> loadedAuthorities = authorityLoadPort.findAllByName(authorities.stream().map(String::toUpperCase).collect(Collectors.toSet()));
        return loadedAuthorities.stream().map(authority -> authority.getId().getValueAsString()).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
