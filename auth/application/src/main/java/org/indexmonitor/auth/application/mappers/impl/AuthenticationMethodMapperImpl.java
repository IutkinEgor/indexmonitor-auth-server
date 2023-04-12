package org.indexmonitor.auth.application.mappers.impl;

import org.indexmonitor.auth.application.mappers.AuthenticationMethodMapper;
import org.indexmonitor.common.domain.enums.AuthenticationMethod;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

@Component
class AuthenticationMethodMapperImpl implements AuthenticationMethodMapper {

    @Override
    public ClientAuthenticationMethod map(AuthenticationMethod appMethod) {
        switch (appMethod) {
            case CLIENT_SECRET_BASIC:
                return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
            case CLIENT_SECRET_POST:
                return ClientAuthenticationMethod.CLIENT_SECRET_POST;
            case CLIENT_SECRET_JWT:
                return ClientAuthenticationMethod.CLIENT_SECRET_JWT;
            case PRIVATE_KEY_JWT:
                return ClientAuthenticationMethod.PRIVATE_KEY_JWT;
            case NONE:
                return ClientAuthenticationMethod.NONE;
            default:
                return null;
        }
    }

    @Override
    public AuthenticationMethod map(ClientAuthenticationMethod method) {
        switch (method.getValue()) {
            case "client_secret_basic":
                return AuthenticationMethod.CLIENT_SECRET_BASIC;
            case "client_secret_post":
                return AuthenticationMethod.CLIENT_SECRET_POST;
            case "client_secret_jwt":
                return AuthenticationMethod.CLIENT_SECRET_JWT;
            case "private_key_jwt":
                return AuthenticationMethod.PRIVATE_KEY_JWT;
            case "none":
                return AuthenticationMethod.NONE;
            default:
                return null;
        }
    }
}
