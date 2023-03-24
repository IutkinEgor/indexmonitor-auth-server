package org.indexmonitor.user.application.ports.out.confirmEmail;

import org.indexmonitor.user.domain.aggregates.ConfirmEmail;

public interface ConfirmEmailSendPort {
    void sendConfirmEmail(ConfirmEmail confirmEmail);
}
