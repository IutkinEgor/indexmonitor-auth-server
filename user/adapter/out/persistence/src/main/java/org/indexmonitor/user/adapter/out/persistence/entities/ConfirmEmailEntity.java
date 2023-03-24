package org.indexmonitor.user.adapter.out.persistence.entities;
import org.indexmonitor.common.domain.enums.EncryptionAlgorithm;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.exceptions.IllegalIdFormatException;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "confirm_email_tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmEmailEntity {
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
    @Column(nullable = false)
    private String redirectUrl;

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new IllegalIdFormatException("Confirm Email");
        }
    }
}
