package org.indexmonitor.common.domain.models;

public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

    public AggregateRoot(ID id) {
        super(id);
    }
}
