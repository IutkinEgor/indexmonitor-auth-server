package org.indexmonitor.user.application.exceptions.resetPasswordExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ResetPasswordTokenFormatException extends ApplicationException {

    private static final String MESSAGE = "Token has an invalid format.";
    public ResetPasswordTokenFormatException() {
        super(MESSAGE);
    }
}
