package org.indexmonitor.user.application.ports.out.role;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.Role;

public interface RoleRegisterPort extends Port {

    BaseId generateId();

    void register(Role role);

}
