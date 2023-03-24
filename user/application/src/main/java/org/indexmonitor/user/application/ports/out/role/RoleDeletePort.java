package org.indexmonitor.user.application.ports.out.role;

import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface RoleDeletePort {
    void delete(BaseId id);
}
