package org.indexmonitor.auth.application.authenticationProviders;

import org.indexmonitor.auth.application.services.AppUserDetailsService;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Setter
public class EmailVerificationCodeAuthenticationProvider implements AuthenticationProvider {

    private AppUserDetailsService appUserDetailsService;
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
