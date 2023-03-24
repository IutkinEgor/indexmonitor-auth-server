package org.indexmonitor.user.application.ports.out.resetPassword;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

public interface ResetPasswordStorePort extends Port {
    void store(ResetPassword resetPassword);
}
