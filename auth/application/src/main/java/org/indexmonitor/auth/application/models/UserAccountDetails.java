package org.indexmonitor.auth.application.models;

import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.models.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class UserAccountDetails implements UserDetails {

    private final User user;

    public UserAccountDetails(User user) {
        this.user = user;
    }

    //Implementation of UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();
        this.user.getAuthorities().forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        });
            //TODO didnt work nned load roles and authorities
        this.user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });

        return authorities;
    }

    public User getUser() { return this.user; }

    @Override
    public String getPassword() {
        return this.user.getPassword().getHash();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    public UserProfile getProfile() {
        return this.user.getProfile();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isUserNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isUserNonLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
