package org.indexmonitor.user.application.ports.out.user;

import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface UserDeletePort {
    void delete(BaseId id);
}
