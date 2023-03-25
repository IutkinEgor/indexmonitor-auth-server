package org.indexmonitor.user.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.models.UserProfile;
import org.indexmonitor.user.domain.valueObjects.Password;
import org.indexmonitor.user.domain.valueObjects.UserAuthority;
import org.indexmonitor.user.domain.valueObjects.UserRole;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class User extends AggregateRoot<BaseId> {

    private final String userName;
    private final Password password;
    private final Set<UserAuthority> authorities;
    private final Set<UserRole> roles;
    private final Instant createdAt;
    private final Boolean isUserNonExpired;
    private final Boolean isUserNonLocked;
    private final Boolean isCredentialsNonExpired;
    private final Boolean isEnabled;
    private final UserProfile profile;

    private User(Builder builder) {
        super(builder.userId);
        this.userName = builder.userName;
        this.password = builder.password;
        this.authorities = builder.authorities == null ? new LinkedHashSet<>() : builder.authorities;
        this.roles = builder.roles == null ? new LinkedHashSet<>() : builder.roles;
        this.createdAt = builder.createdAt;
        this.isUserNonExpired = builder.isUserNonExpired;
        this.isUserNonLocked = builder.isUserNonLocked;
        this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
        this.isEnabled = builder.isEnabled;
        this.profile = builder.profile;
    }

    public String getUserName() {
        return userName;
    }

    public Password getPassword() {
        return password;
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }
    public Set<String> getAuthoritiesNames() {
        return authorities.stream().map(authority -> authority.getAuthorityName()).collect(Collectors.toSet());
    }
    public Set<UserRole> getRoles() {
        return roles;
    }
    public Set<String> getRolesNames() {
        return roles.stream().map(role -> role.getRoleName()).collect(Collectors.toSet());
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public Boolean isUserNonExpired() {
        return isUserNonExpired;
    }
    public Boolean isUserNonLocked() {
        return isUserNonLocked;
    }
    public Boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    public Boolean isEnabled() {
        return isEnabled;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public static Builder builder() {
        return new Builder();
    }

    public User updatePassword(Password password){
        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .password(password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .profile(this.getProfile())
                .build();
    }
    public User updateRoles(Set<UserRole> roles){
        return User.builder()
                .userId(this.getId())
                .userName(this.userName)
                .password(this.password)
                .authorities(this.authorities)
                .roles(roles == null ? new LinkedHashSet<>() : roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .profile(this.profile)
                .build();
    }

    public User updateAuthorities(Set<UserAuthority> authorities){
        return User.builder()
                .userId(this.getId())
                .userName(this.userName)
                .password(this.password)
                .authorities(authorities == null ? new LinkedHashSet<>() : authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .profile(this.profile)
                .build();
    }
    public User updateIsNonLocked(boolean nonLocked){
        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .profile(this.getProfile())
                .password(this.password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(nonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .build();
    }
    public User updateIsNonExpired(boolean nonExpired){
        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .profile(this.getProfile())
                .password(this.password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(nonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .build();
    }
    public User updateIsCredentialsNonExpired(boolean credentialsNonExpired){
        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .profile(this.getProfile())
                .password(this.password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isCredentialsNonExpired)
                .isCredentialsNonExpired(credentialsNonExpired)
                .isEnabled(this.isEnabled)
                .build();
    }
    public User updateIsEnable(boolean enable){
        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .profile(this.getProfile())
                .password(this.password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(enable)
                .build();
    }
    public User updateProfileEmailConfirmed(boolean emailConfirmed){
        UserProfile profile = UserProfile.builder()
                .profileId(this.getProfile().getId())
                .givenName(this.getProfile().getGivenName())
                .familyName(this.getProfile().getFamilyName())
                .email(this.getProfile().getEmail())
                .emailConfirmed(emailConfirmed)
                .recovery(this.getProfile().getRecovery())
                .build();

        return User.builder()
                .userId(this.getId())
                .userName(this.getUserName())
                .profile(profile)
                .password(this.password)
                .authorities(this.authorities)
                .roles(this.roles)
                .createdAt(this.getCreatedAt())
                .isUserNonLocked(this.isUserNonLocked)
                .isUserNonExpired(this.isUserNonExpired)
                .isCredentialsNonExpired(this.isCredentialsNonExpired)
                .isEnabled(this.isEnabled)
                .build();
    }
    
    public UserUpdateBuilder userUpdateBuilder(){
        return new UserUpdateBuilder(this);
    }
    public static final class UserUpdateBuilder{
        private User user;
        private String userName;
        private Password password;
        private Set<UserAuthority> authorities;
        private Set<UserRole> roles;
        private Boolean isUserNonExpired;
        private Boolean isUserNonLocked;
        private Boolean isCredentialsNonExpired;
        private Boolean isEnabled;
        public UserUpdateBuilder(User user) {
            this.user = user;
            this.userName = user.getUserName();
            this.password = user.getPassword();
            this.authorities = user.getAuthorities();
            this.roles = user.getRoles();
            this.isUserNonExpired = user.isUserNonExpired();
            this.isUserNonLocked = user.isUserNonLocked();
            this.isCredentialsNonExpired = user.isCredentialsNonExpired();
            this.isEnabled = user.isEnabled();
        }
        public UserUpdateBuilder userName(String val){
            this.userName = val;
            return this;
        }
        public UserUpdateBuilder password(Password val){
            this.password = val;
            return this;
        }
        public UserUpdateBuilder authorities(Set<UserAuthority> val){
            this.authorities = val;
            return this;
        }
        public UserUpdateBuilder roles(Set<UserRole> val){
            this.roles = val;
            return this;
        }
        public UserUpdateBuilder isUserNonExpired(Boolean val){
            this.isUserNonExpired = val;
            return this;
        }
        public UserUpdateBuilder isUserNonLocked(Boolean val){
            this.isUserNonLocked = val;
            return this;
        }
        public UserUpdateBuilder isCredentialsNonExpired(Boolean val){
            this.isCredentialsNonExpired = val;
            return this;
        }
        public UserUpdateBuilder isEnabled(Boolean val){
            this.isEnabled = val;
            return this;
        }
        public User build(){
            return User.builder()
                    .userId(this.user.getId())
                    .userName(userName)
                    .profile(this.user.getProfile())
                    .password(password)
                    .authorities(authorities)
                    .roles(roles)
                    .createdAt(this.user.getCreatedAt())
                    .isUserNonLocked(isUserNonLocked)
                    .isUserNonExpired(isUserNonExpired)
                    .isCredentialsNonExpired(isCredentialsNonExpired)
                    .isEnabled(isEnabled)
                    .build();
        }
    }

    public UserProfileUpdateBuilder userProfileUpdateBuilder(){
        return new UserProfileUpdateBuilder(this);
    }
    public static final class UserProfileUpdateBuilder{
        private User user;
        private String givenName;
        private String familyName;
        private String email;
        private Boolean isEmailConfirmed;

        public UserProfileUpdateBuilder(User user) {
            this.user = user;
            this.givenName = user.getProfile().getGivenName();
            this.familyName = user.getProfile().getFamilyName();
            this.email = user.getProfile().getEmail();
            this.isEmailConfirmed = user.getProfile().isEmailConfirmed();
        }

        public UserProfileUpdateBuilder givenName(String val){
            this.givenName = val;
            return this;
        }
        public UserProfileUpdateBuilder familyName(String val) {
            this.familyName = val;
            return this;
        }
        public UserProfileUpdateBuilder email(String val){
            this.email = val;
            return this;
        }
        public UserProfileUpdateBuilder isEmailConfirmed(Boolean val){
            this.isEmailConfirmed = val;
            return this;
        }
        public User build(){
            UserProfile profile = UserProfile.builder()
                    .profileId(this.user.getProfile().getId())
                    .givenName(this.givenName)
                    .familyName(this.familyName)
                    .email(this.email)
                    .emailConfirmed(this.isEmailConfirmed)
                    .recovery(this.user.getProfile().getRecovery())
                    .build();

            return User.builder()
                    .userId(this.user.getId())
                    .userName(this.user.getUserName())
                    .profile(profile)
                    .password(this.user.getPassword())
                    .authorities(this.user.getAuthorities())
                    .roles(this.user.getRoles())
                    .createdAt(this.user.getCreatedAt())
                    .isUserNonLocked(this.user.isUserNonLocked())
                    .isUserNonExpired(this.user.isCredentialsNonExpired())
                    .isCredentialsNonExpired(this.user.isCredentialsNonExpired())
                    .isEnabled(this.user.isEnabled())
                    .build();
        }
    }

    public static final class Builder {
        private BaseId userId;
        private String userName;
        private Password password;
        private Set<UserAuthority> authorities;
        private Set<UserRole> roles;
        private UserProfile profile;
        private Instant createdAt;
        private Boolean isUserNonExpired = false;
        private Boolean isUserNonLocked = false;
        private Boolean isCredentialsNonExpired = false;
        private Boolean isEnabled = false;
        private Builder() {
        }
        public Builder userId(BaseId val) {
            this.userId = val;
            return this;
        }
        public Builder userName(String val) {
            this.userName = val;
            return this;
        }
        public Builder password(Password val) {
            this.password = val;
            return this;
        }
        public Builder authorities(Set<UserAuthority> val) {
            this.authorities = val == null ? new LinkedHashSet<>() : val;
            return this;
        }
        public Builder authorities(UserAuthority... val) {
            this.authorities = val == null ? new LinkedHashSet<>() : Arrays.stream(val).collect(Collectors.toSet());
            return this;
        }
        public Builder roles(Set<UserRole> val) {
            this.roles = val == null ? new LinkedHashSet<>() : val;
            return this;
        }
        public Builder roles(UserRole... val) {
            this.roles = val == null ? new LinkedHashSet<>() : Arrays.stream(val).collect(Collectors.toSet());;
            return this;
        }
        public Builder createdAt(Instant val) {
            this.createdAt = val;
            return this;
        }
        public Builder isUserNonExpired(Boolean val) {
            this.isUserNonExpired = val;
            return this;
        }
        public Builder isUserNonLocked(Boolean val) {
            this.isUserNonLocked = val;
            return this;
        }
        public Builder isCredentialsNonExpired(Boolean val) {
            this.isCredentialsNonExpired = val;
            return this;
        }
        public Builder isEnabled(Boolean val) {
            this.isEnabled = val;
            return this;
        }
        public Builder profile(UserProfile val) {
            this.profile = val;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}