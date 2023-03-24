package org.indexmonitor.common.domain.valueObjects;

import java.io.Serializable;
import java.util.Objects;

public class BaseId<ID> implements Serializable {
    private final ID id;

    public BaseId(ID id) {
        this.id = id;
    }

    public ID getValue() {
        return id;
    }
    public String getValueAsString() { return id.toString();  }

    public static BaseId map(Object id) {
        return new BaseId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseId<?> baseId = (BaseId<?>) o;

        return Objects.equals(id, baseId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
