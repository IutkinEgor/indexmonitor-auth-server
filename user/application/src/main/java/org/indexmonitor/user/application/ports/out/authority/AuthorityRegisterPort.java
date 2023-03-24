package org.indexmonitor.user.application.ports.out.authority;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.Authority;

public interface AuthorityRegisterPort extends Port {

    BaseId generateId();

    void register(Authority authority);

}
