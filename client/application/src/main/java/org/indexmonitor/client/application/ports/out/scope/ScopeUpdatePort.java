package org.indexmonitor.client.application.ports.out.scope;

import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.interfaces.Port;

public interface ScopeUpdatePort extends Port {
    void update(Scope scope);
}
