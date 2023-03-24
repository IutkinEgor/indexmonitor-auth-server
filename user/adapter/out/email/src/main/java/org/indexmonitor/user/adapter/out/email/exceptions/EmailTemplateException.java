package org.indexmonitor.user.adapter.out.email.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class EmailTemplateException extends AdapterException {

    private static final String MESSAGE = "Error while building email template";

    public EmailTemplateException() {
        super(MESSAGE);
    }
    public EmailTemplateException(String message) {
        super(message);
    }
}
