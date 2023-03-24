package org.indexmonitor.client.application.models;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProjection {
    private final BaseId id;
    private final String username;
    private final String firstName;
    private final String secondName;
}
