package org.indexmonitor.client.adapter.out.persistence.entities;

import org.indexmonitor.client.adapter.out.persistence.exceptions.ScopeIllegalIdException;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "scopes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScopeEntity {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Instant createdAt;
    private String createdBy;
    @Column(nullable = false)
    private Boolean isEnable;
    @Column(nullable = false)
    private Boolean isObtainable;

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new ScopeIllegalIdException();
        }
    }
}
