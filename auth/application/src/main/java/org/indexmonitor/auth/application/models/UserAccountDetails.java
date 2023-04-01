package org.indexmonitor.auth.application.models;

import org.indexmonitor.user.domain.aggregates.User;
import org.indexmonitor.user.domain.models.UserProfile;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.UserAuthority;
import org.indexmonitor.user.domain.valueObjects.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class UserAccountDetails implements UserDetails {
    private String userId;
    private String username;
    private String password;
    private String givenName;
    private String familyName;
    private String email;
    private Boolean emailConfirmed;
    private Set<String> authorities;
    private Set<String> roles;
    private Instant createdAt;
    private Boolean isUserNonExpired;
    private Boolean isUserNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    public UserAccountDetails() {

    }
    public UserAccountDetails(User user) {
        this.userId = user.getId().getValueAsString();
        this.username = user.getUserName();
        this.password = user.getPassword().getHash();
        this.givenName = user.getProfile().getGivenName();
        this.familyName = user.getProfile().getFamilyName();
        this.email = user.getProfile().getEmail();
        this.emailConfirmed = user.getProfile().isEmailConfirmed();
        this.authorities = user.getAuthoritiesNames();
        this.roles = user.getRolesNames();
        this.isUserNonExpired = user.isUserNonExpired();
        this.isUserNonLocked = user.isUserNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
    }

    //Implementation of UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        this.authorities.forEach(authority -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        });
        this.roles.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        return grantedAuthorities;
    }

    public String getUserId() {
        return userId;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isUserNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isUserNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }
    public Boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public Set<String> getRolesName() {
        return roles;
    }

    public Set<String> getAuthoritiesName() {
        return authorities;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
