package org.indexmonitor.auth.application.ports.out;

import org.indexmonitor.auth.application.models.Authorization;
import org.indexmonitor.common.domain.interfaces.Port;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

public interface AuthorizationSavePort extends Port {
    void save(OAuth2Authorization authorization);
}
