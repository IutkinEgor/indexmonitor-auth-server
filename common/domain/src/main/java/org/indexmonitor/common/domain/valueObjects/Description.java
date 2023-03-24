package org.indexmonitor.common.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;

public class Description extends ValueObject {
    private final String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
