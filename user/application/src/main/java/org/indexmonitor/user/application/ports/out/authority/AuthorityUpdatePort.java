package org.indexmonitor.user.application.ports.out.authority;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.user.domain.aggregates.Authority;

public interface AuthorityUpdatePort extends Port {
    void update(Authority authority);
}
