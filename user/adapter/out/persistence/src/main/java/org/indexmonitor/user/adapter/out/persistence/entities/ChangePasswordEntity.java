package org.indexmonitor.user.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.exceptions.IllegalIdFormatException;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "change_password_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordEntity {
    @Id
    private UUID id;
    @Column(unique = true, nullable = false)
    private String tokenHash;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm tokenAlgorithm;
    @Column(nullable = false)
    private Instant issuedAt;
    @Column(nullable = false)
    private Instant expireAt;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity user;
    @Column
    private String redirectUrl;
    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new IllegalIdFormatException("Reset password");
        }
    }
}
