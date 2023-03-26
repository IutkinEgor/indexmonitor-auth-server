package org.indexmonitor.user.application.ports.out.resetPassword;

import org.indexmonitor.user.domain.aggregates.ResetPassword;

public interface ResetPasswordSendPort {

    void send(ResetPassword resetPassword);
}
