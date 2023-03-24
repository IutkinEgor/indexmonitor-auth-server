package org.indexmonitor.user.adapter.out.email.services;

import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordSendLinkPort;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordSendLinkPortService implements ResetPasswordSendLinkPort {
    @Override
    public void send(String email, String link) {
        System.out.println("Reset password link: " + link);
    }
}
