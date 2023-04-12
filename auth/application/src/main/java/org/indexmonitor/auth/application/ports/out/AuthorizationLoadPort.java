package org.indexmonitor.auth.application.ports.out;

import org.indexmonitor.common.domain.interfaces.Port;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.Optional;

public interface AuthorizationLoadPort extends Port {
    Optional<OAuth2Authorization> findById(String id);
    Optional<OAuth2Authorization> findByTokenValue(String token);
}
