package org.indexmonitor.auth.application.authenticationProviders;

import org.indexmonitor.auth.application.exceptions.EmailNotConfirmedAuthenticationException;
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
public class UserDisabledAuthenticationProvider  implements AuthenticationProvider {
    private AppUserDetailsService appUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        final String email = (String) authenticationToken.getPrincipal();
        final String password = (String) authenticationToken.getCredentials();

        UserAccountDetails userAccountDetails = appUserDetailsService.loadUserByEmail(email);

        if(userAccountDetails != null && passwordEncoder.matches(password, userAccountDetails.getPassword())){

            if(!userAccountDetails.getProfile().isEmailConfirmed()){
                throw new EmailNotConfirmedAuthenticationException(email);
            }
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            authoritySet.addAll(userAccountDetails.getAuthorities());
            return org.indexmonitor.auth.application.authenticationToken.UsernamePasswordAuthenticationToken.authenticated(email, null, authoritySet);
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.getClass().equals(UsernamePasswordAuthenticationToken.class);
    }
}
