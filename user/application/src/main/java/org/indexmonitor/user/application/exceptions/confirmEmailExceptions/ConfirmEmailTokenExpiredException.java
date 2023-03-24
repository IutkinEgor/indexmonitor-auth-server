package org.indexmonitor.user.application.exceptions.confirmEmailExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ConfirmEmailTokenExpiredException extends ApplicationException {
    private final static String MESSAGE = "The confirmation link in your email has expired. A new link has been sent to your email address.";

    public ConfirmEmailTokenExpiredException() {
        super(MESSAGE);
    }

}
