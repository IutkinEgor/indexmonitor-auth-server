package org.indexmonitor.auth.application.services;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.application.ports.out.AuthorizationLoadPort;
import org.indexmonitor.auth.application.ports.out.AuthorizationRemovePort;
import org.indexmonitor.auth.application.ports.out.AuthorizationSavePort;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final AuthorizationSavePort authorizationSavePort;
    private final AuthorizationLoadPort authorizationLoadPort;
    private final AuthorizationRemovePort authorizationRemovePort;

    @Override
    public void save(OAuth2Authorization authorization) {
        authorizationSavePort.save(authorization);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        authorizationRemovePort.remove(authorization);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return authorizationLoadPort.findById(id).get();
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return authorizationLoadPort.findByTokenValue(token).get();
    }
}
