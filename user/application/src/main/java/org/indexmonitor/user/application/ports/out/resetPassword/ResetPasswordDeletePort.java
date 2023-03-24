package org.indexmonitor.user.application.ports.out.resetPassword;

import org.indexmonitor.common.domain.valueObjects.BaseId;

public interface ResetPasswordDeletePort {

    void deleteById(BaseId id);

    void deleteByUserId(BaseId userId);
}
