package org.indexmonitor.auth.application.authenticationToken;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

public class EmailPasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String email;
    private String password;

    public EmailPasswordAuthenticationToken(String email, String password) {
        super(null);
        this.email = email;
        this.password = password;
        setAuthenticated(false);
    }
    public EmailPasswordAuthenticationToken(String email, String password,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
        this.password = password;
        super.setAuthenticated(true); // must use super, as we override
    }
    public static EmailPasswordAuthenticationToken unauthenticated(String email, String password) {
        return new EmailPasswordAuthenticationToken(email, password);
    }
    public static EmailPasswordAuthenticationToken authenticated(String email, String password,
                                                                    Collection<? extends GrantedAuthority> authorities) {
        return new EmailPasswordAuthenticationToken(email, password, authorities);
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() { return this.password; }

    @Override
    public String getPrincipal() { return this.password; }

    @Override
    public String getCredentials() {
        return this.email;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.password = null;
    }
}
