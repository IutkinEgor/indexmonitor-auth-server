package org.indexmonitor.user.application.ports.out.changePassword;

import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

public interface ChangePasswordSendPort {

    void send(ChangePassword changePassword);
}
