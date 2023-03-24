package org.indexmonitor.client.adapter.out.persistence.entities;

import org.indexmonitor.client.adapter.out.persistence.exceptions.ScopeIllegalIdException;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProjection {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    public static UserProjection build(BaseId id){
        return new UserProjection(convertId(id));
    }



    public BaseId getBaseId(){
        return new BaseId<UUID>(userId);
    }

    public static UUID convertId(BaseId id){
        try{
            return UUID.fromString(id.getValue().toString());
        }
        catch (Exception e) {
            throw new ScopeIllegalIdException();
        }
    }
}
