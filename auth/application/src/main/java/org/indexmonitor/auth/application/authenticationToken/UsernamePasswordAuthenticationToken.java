package org.indexmonitor.auth.application.authenticationToken;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private final String password;

    public UsernamePasswordAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
        setAuthenticated(false);
    }
    public UsernamePasswordAuthenticationToken(String username, String password,
                                            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        super.setAuthenticated(true); // must use super, as we override
    }
    public static UsernamePasswordAuthenticationToken unauthenticated(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
    public static UsernamePasswordAuthenticationToken authenticated(String username, String password,
                                                                 Collection<? extends GrantedAuthority> authorities) {
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }


    @Override
    public String getCredentials() {
        return this.username;
    }

    @Override
    public String getPrincipal() {
        return this.password;
    }
}
