package org.indexmonitor.user.application.ports.out.resetPassword;

public interface ResetPasswordSendLinkPort {

    void send(String email, String link);
}
