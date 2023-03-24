package org.indexmonitor.user.adapter.out.email.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class EmailException extends AdapterException {
    private static String MESSAGE = "Email exception";

    public EmailException() {
        super(MESSAGE);
    }
    public EmailException(String message) {
        super(message);
    }
}
