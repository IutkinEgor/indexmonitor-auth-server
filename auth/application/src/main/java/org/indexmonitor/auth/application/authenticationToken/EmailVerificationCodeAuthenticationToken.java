package org.indexmonitor.auth.application.authenticationToken;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmailVerificationCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final String email;

    public EmailVerificationCodeAuthenticationToken(String email) {
        super(null);
        this.email = email;
    }

    public EmailVerificationCodeAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
    }

    public static EmailVerificationCodeAuthenticationToken unauthenticated(String email) {
        return new EmailVerificationCodeAuthenticationToken(email);
    }

    public static EmailVerificationCodeAuthenticationToken authenticated(String email, Collection<? extends GrantedAuthority> authorities) {
        return new EmailVerificationCodeAuthenticationToken(email, authorities);
    }

    @Override
    public String getCredentials() {
        return email;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
