package org.indexmonitor.user.application.ports.out.user;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.user.domain.aggregates.User;

public interface UserUpdatePort extends Port {
    void update(User user);
}
