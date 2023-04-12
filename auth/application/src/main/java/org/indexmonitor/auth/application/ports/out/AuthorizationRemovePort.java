package org.indexmonitor.auth.application.ports.out;

import org.indexmonitor.auth.application.models.Authorization;
import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

public interface AuthorizationRemovePort extends Port {
    void remove(OAuth2Authorization authorization);
}
