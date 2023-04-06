package org.indexmonitor.user.adapter.out.persistence.entities;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.exceptions.AuthorityIllegalIdException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity {

    @Id
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(length = 2000)
    private String description;
    private String createdBy;
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Boolean isEnable;
    @Column(nullable = false)
    private Boolean isObtainable;

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new AuthorityIllegalIdException();
        }
    }
}
