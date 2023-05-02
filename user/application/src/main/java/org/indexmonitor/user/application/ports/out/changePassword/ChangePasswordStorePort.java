package org.indexmonitor.user.application.ports.out.changePassword;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

public interface ChangePasswordStorePort extends Port {
    void store(ChangePassword changePassword);
}
