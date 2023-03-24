package org.indexmonitor.user.application.ports.out.authority;

import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface AuthorityDeletePort {
    void delete(BaseId id);
}
