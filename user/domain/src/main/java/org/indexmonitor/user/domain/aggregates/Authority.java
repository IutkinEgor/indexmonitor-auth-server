package org.indexmonitor.user.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.time.Instant;

public class Authority extends AggregateRoot<BaseId> {
    private final String name;
    private final String description;
    private final BaseId createdBy;
    private final Instant createdAt;
    private final Boolean isEnable;
    private final Boolean isObtainable;
    private Authority(Builder builder) {
        super(builder.id);
        name = builder.name.toUpperCase();
        description = builder.description;
        createdBy = builder.createdBy;
        createdAt = builder.createdAt;
        isEnable = builder.isEnable;
        isObtainable = builder.isObtainable;
    }
    public String getName() {
        return this.name;
    }

    public String getDescription() { return this.description; }
    public BaseId getCreatedBy() {
        return createdBy;
    }
    public Instant getCreatedAt() {
        return createdAt;
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
        private BaseId id;
        private String name;
        private String description;
        private BaseId createdBy;
        private Instant createdAt;
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
        public Builder createdBy(BaseId val) {
            createdBy = val;
            return this;
        }

        public Builder createdAt(Instant val) {
            createdAt = val;
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

        public Authority build() {
            return new Authority(this);
        }
    }
}
