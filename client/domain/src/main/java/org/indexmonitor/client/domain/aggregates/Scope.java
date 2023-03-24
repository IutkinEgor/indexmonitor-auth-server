package org.indexmonitor.client.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.time.Instant;
import java.util.Objects;

public class Scope extends AggregateRoot<BaseId> {
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final BaseId createdBy;
    private final Boolean isEnable;
    private final Boolean isObtainable;

    private Scope(Builder builder) {
        super(builder.id);
        name = builder.name;
        description = builder.description;
        createdAt = builder.createdAt;
        createdBy = builder.createdBy;
        isEnable = builder.isEnable;
        isObtainable = builder.isObtainable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BaseId getCreatedBy() {
        return createdBy;
    }

    public Boolean isEnable() {
        return isEnable;
    }

    public Boolean isObtainable() {
        return isObtainable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private BaseId<Long> id;
        private String name;
        private String description;
        private Instant createdAt;
        private BaseId createdBy;
        private Boolean isEnable;
        private Boolean isObtainable;

        private Builder() {
        }

        public Builder id(BaseId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder createdAt(Instant val) {
            createdAt = val;
            return this;
        }

        public Builder createdBy(BaseId val) {
            createdBy = val;
            return this;
        }

        public Builder isEnable(Boolean val) {
            isEnable = val;
            return this;
        }

        public Builder isObtainable(Boolean val) {
            isObtainable = val;
            return this;
        }

        public Scope build() {
            return new Scope(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Scope scope = (Scope) o;
        if(!Objects.equals(this.getId(),scope.getId())) return false;
        if (!Objects.equals(name, scope.name)) return false;
        if (!Objects.equals(description, scope.description)) return false;
        if (!Objects.equals(createdAt, scope.createdAt)) return false;
        if (!Objects.equals(createdBy, scope.createdBy)) return false;
        if (!Objects.equals(isEnable, scope.isEnable)) return false;
        return Objects.equals(isObtainable, scope.isObtainable);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
//        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
//        result = 31 * result + (isEnable != null ? isEnable.hashCode() : 0);
//        result = 31 * result + (isObtainable != null ? isObtainable.hashCode() : 0);
        return result;
    }
}
