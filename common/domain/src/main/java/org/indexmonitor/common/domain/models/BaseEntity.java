package org.indexmonitor.common.domain.models;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    private final ID id;
    public  BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
