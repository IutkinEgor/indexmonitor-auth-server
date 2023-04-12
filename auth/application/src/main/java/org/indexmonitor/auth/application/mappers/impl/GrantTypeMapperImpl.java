package org.indexmonitor.auth.application.mappers.impl;

import org.indexmonitor.auth.application.mappers.GrantTypeMapper;
import org.indexmonitor.common.domain.enums.OAuthGrantType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

@Component
class GrantTypeMapperImpl implements GrantTypeMapper {

    @Override
    public AuthorizationGrantType map(OAuthGrantType appMethod) {
        switch (appMethod) {
            case AUTHORIZATION_CODE:
                return AuthorizationGrantType.AUTHORIZATION_CODE;
            case CLIENT_CREDENTIALS:
                return AuthorizationGrantType.CLIENT_CREDENTIALS;
            case RESOURCE_OWNER_PASSWORD_CREDENTIALS:
                return AuthorizationGrantType.PASSWORD;
            case REFRESH_TOKEN:
                return AuthorizationGrantType.REFRESH_TOKEN;
            default:
                return null;
        }
    }

    @Override
    public OAuthGrantType map(AuthorizationGrantType method) {
        switch (method.getValue()) {
            case "authorization_code":
                return OAuthGrantType.AUTHORIZATION_CODE;
            case "client_credentials":
                return OAuthGrantType.CLIENT_CREDENTIALS;
            case "password":
                return OAuthGrantType.RESOURCE_OWNER_PASSWORD_CREDENTIALS;
            case "refresh_token":
                return OAuthGrantType.REFRESH_TOKEN;
            default:
                return null;
        }
    }

}
