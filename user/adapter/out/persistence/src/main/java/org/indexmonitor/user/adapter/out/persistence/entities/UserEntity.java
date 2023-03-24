package org.indexmonitor.user.adapter.out.persistence.entities;

import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.exceptions.UserIllegalIdException;
import org.indexmonitor.user.domain.valueObjects.UserAuthority;
import org.indexmonitor.user.domain.valueObjects.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfileEntity profile;
    private Instant createdAt;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;
    @Transient
    private Set<UserAuthority> domainAuthorities;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
    @Transient
    private Set<UserRole> domainRoles;

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new UserIllegalIdException();
        }
    }

}
