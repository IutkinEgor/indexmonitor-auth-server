package org.indexmonitor.user.application.ports.out.changePassword;

import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface ChangePasswordDeletePort {

    void deleteById(BaseId id);
}
