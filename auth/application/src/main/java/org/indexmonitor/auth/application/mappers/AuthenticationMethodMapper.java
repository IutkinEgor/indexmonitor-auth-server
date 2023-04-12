package org.indexmonitor.auth.application.mappers;


import org.indexmonitor.common.domain.enums.AuthenticationMethod;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public interface AuthenticationMethodMapper {

    ClientAuthenticationMethod map(AuthenticationMethod appMethod);
    AuthenticationMethod map(ClientAuthenticationMethod method);
}
