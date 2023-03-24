package org.indexmonitor.auth.application.authenticationProviders;

import org.indexmonitor.auth.application.authenticationToken.EmailPasswordAuthenticationToken;
import org.indexmonitor.auth.application.models.UserAccountDetails;
import org.indexmonitor.auth.application.services.AppUserDetailsService;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Setter
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {

    private AppUserDetailsService appUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        final String email = (String) authenticationToken.getPrincipal();
        final String password = (String) authenticationToken.getCredentials();

        UserAccountDetails userAccountDetails = appUserDetailsService.loadUserByEmail(email);

        if(userAccountDetails != null && passwordEncoder.matches(password, userAccountDetails.getPassword())){
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            authoritySet.addAll(userAccountDetails.getAuthorities());
            return EmailPasswordAuthenticationToken.authenticated(email, null, authoritySet);
        }
        return EmailPasswordAuthenticationToken.unauthenticated(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailPasswordAuthenticationToken.class.equals(authentication);
    }



/*    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailPasswordAuthenticationToken emailPasswordAuthenticationToken = (EmailPasswordAuthenticationToken) authentication;

        final String email = emailPasswordAuthenticationToken.getPrincipal();
        final String password = emailPasswordAuthenticationToken.getCredentials();

        UserAccountDetails userAccountDetails = userAccountDetailsService.loadUserByEmail(email);

        if(userAccountDetails != null && passwordEncoder.matches(password, userAccountDetails.getPassword())){
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            authoritySet.addAll(userAccountDetails.getAuthorities());
           return EmailPasswordAuthenticationToken.authenticated(email, null, authoritySet);
        }
        return EmailPasswordAuthenticationToken.unauthenticated(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailPasswordAuthenticationToken.class.equals(authentication);
    }*/
}
