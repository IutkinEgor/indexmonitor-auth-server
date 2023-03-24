package org.indexmonitor.user.application.exceptions.confirmEmailExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ConfirmEmailNotFoundException extends ApplicationException {
    private static final String MESSAGE = "Confirm email not found.";
    public ConfirmEmailNotFoundException() {
        super(MESSAGE);
    }

}
