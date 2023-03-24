package org.indexmonitor.user.adapter.out.persistence.entities;


import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.exceptions.UserProfileIllegalIdException;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileEntity {

    @Id
    @Column(name = "profile_id", unique = true)
    private UUID profileId;

    @Column(nullable = false)
    private String givenName;

    @Column(nullable = false)
    private String familyName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean email_confirmed;

    @Column(nullable = false)
    private String recoveryQuestion;

    @Column(nullable = false)
    private String recoveryAnswerHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm recoveryAnswerAlgorithm;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private UserEntity user;

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new UserProfileIllegalIdException();
        }
    }
}
