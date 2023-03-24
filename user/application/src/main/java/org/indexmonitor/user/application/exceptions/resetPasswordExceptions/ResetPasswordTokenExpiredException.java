package org.indexmonitor.user.application.exceptions.resetPasswordExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ResetPasswordTokenExpiredException extends ApplicationException {
    private final static String MESSAGE = "The link in your email has expired.";

    public ResetPasswordTokenExpiredException() {
        super(MESSAGE);
    }
}
