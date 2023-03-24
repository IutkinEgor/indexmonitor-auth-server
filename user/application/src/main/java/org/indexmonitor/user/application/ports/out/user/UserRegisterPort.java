package org.indexmonitor.user.application.ports.out.user;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.aggregates.User;

public interface UserRegisterPort extends Port {

    BaseId getNewId();
    BaseId register(User user);

}
