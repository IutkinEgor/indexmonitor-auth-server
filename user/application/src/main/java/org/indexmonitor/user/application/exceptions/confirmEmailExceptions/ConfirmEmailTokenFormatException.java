package org.indexmonitor.user.application.exceptions.confirmEmailExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ConfirmEmailTokenFormatException extends ApplicationException {

    private final static String MESSAGE = "Confirm email link has an invalid structure.";

    public ConfirmEmailTokenFormatException() {
        super(MESSAGE);
    }

}
