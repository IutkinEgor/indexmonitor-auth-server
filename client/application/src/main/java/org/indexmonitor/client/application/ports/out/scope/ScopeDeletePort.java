package org.indexmonitor.client.application.ports.out.scope;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface ScopeDeletePort extends Port {
    void delete(BaseId id);
}
