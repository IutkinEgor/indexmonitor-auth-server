package org.indexmonitor.client.application.ports.out.scope;

import org.indexmonitor.client.domain.aggregates.Scope;
import org.indexmonitor.common.domain.interfaces.Port;

public interface ScopeRegisterPort extends Port {
    void register(Scope scope);
}
