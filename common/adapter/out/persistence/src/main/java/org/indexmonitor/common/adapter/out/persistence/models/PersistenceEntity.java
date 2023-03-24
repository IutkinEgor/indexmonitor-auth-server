package org.indexmonitor.common.adapter.out.persistence.models;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public abstract class PersistenceEntity<T> {
    @Id
    @Column(name = "id")
    private BaseId<T> id;

    public PersistenceEntity() {
    }
    public PersistenceEntity(T id) {
        this.id = new BaseId<>(id);
    }
    public PersistenceEntity(BaseId id) {
        this.id = id;
    }

    public T getId() {
        return this.id.getValue();
    }
    public void setId(T id) {
        this.id = new BaseId<T>(id);
    }
    public BaseId<T> getBaseId() {
        return this.id;
    }
    public void setBaseId(BaseId id) {
        this.id = id;
    }

    private void validateType(BaseId<T> id){
        if(!(id.getValue() instanceof T)){
            throw new IllegalArgumentException("Wrong BaseId type");
        }
    }
}
