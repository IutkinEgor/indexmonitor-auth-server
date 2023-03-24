package org.indexmonitor.user.application.ports.out.role;

import org.indexmonitor.user.domain.aggregates.Role;

public interface RoleUpdatePort {
    void update(Role role);
}
