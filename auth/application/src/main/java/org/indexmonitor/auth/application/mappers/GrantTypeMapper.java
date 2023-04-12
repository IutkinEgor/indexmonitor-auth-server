package org.indexmonitor.auth.application.mappers;

import org.indexmonitor.common.domain.enums.OAuthGrantType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public interface GrantTypeMapper {

    AuthorizationGrantType map(OAuthGrantType appMethod);
    OAuthGrantType map(AuthorizationGrantType method);
}
