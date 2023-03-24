package org.indexmonitor.auth.application.exceptions;

import org.springframework.security.authentication.DisabledException;

import javax.naming.AuthenticationException;

public class EmailNotConfirmedAuthenticationException extends DisabledException {

    private final String email;

    public EmailNotConfirmedAuthenticationException(String email) {
        super("Account disabled. Email not confirmed.");
        this.email = email;
    }
}
