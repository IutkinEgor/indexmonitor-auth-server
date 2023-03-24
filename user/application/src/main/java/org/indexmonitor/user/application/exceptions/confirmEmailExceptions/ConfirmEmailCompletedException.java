package org.indexmonitor.user.application.exceptions.confirmEmailExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ConfirmEmailCompletedException extends ApplicationException {

    private final static String MESSAGE = "Email already confirmed.";

    public ConfirmEmailCompletedException() {
        super(MESSAGE);
    }

}
