package org.indexmonitor.auth.application.authenticationProviders;

import org.indexmonitor.auth.application.authenticationToken.UsernamePasswordAuthenticationToken;
import org.indexmonitor.auth.application.models.UserAccountDetails;
import org.indexmonitor.auth.application.services.AppUserDetailsService;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Setter
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private AppUserDetailsService appUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        final String username = usernamePasswordAuthenticationToken.getPrincipal();
        final String password = usernamePasswordAuthenticationToken.getCredentials();

        UserAccountDetails userAccountDetails = appUserDetailsService.loadUserByEmail(username);

        if(userAccountDetails != null && passwordEncoder.matches(password, userAccountDetails.getPassword())){
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            authoritySet.addAll(userAccountDetails.getAuthorities());
            return UsernamePasswordAuthenticationToken.authenticated(username, null, authoritySet);
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }


}
