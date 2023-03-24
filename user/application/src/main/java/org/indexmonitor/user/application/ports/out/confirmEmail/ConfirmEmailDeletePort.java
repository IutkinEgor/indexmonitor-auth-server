package org.indexmonitor.user.application.ports.out.confirmEmail;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface ConfirmEmailDeletePort extends Port {

    void deleteById(BaseId id);

    void deleteByUserId(BaseId userId);
}
