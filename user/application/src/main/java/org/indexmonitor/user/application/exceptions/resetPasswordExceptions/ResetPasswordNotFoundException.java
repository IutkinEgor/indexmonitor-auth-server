package org.indexmonitor.user.application.exceptions.resetPasswordExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ResetPasswordNotFoundException extends ApplicationException {
    private static final String MESSAGE = "Token not found.";
    public ResetPasswordNotFoundException() {
        super(MESSAGE);
    }
}
