package org.indexmonitor.auth.application.services;

import org.indexmonitor.auth.application.models.UserAccountDetails;
import org.indexmonitor.user.application.ports.out.user.UserLoadPort;
import org.indexmonitor.user.domain.aggregates.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserLoadPort userLoadPort;

    @Override
    public UserAccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO To make possible login via email instead of username. Need other provider or filter.
        Optional<User> userAccount = userLoadPort.findByEmail(username);
        return new UserAccountDetails(userAccount.get());
    }

    public UserAccountDetails loadUserByEmail(String email) {
        Optional<User> userAccount = userLoadPort.findByEmail(email);
        return new UserAccountDetails(userAccount.get());
    }
}
