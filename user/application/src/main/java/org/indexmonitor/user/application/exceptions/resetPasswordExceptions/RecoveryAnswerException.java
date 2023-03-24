package org.indexmonitor.user.application.exceptions.resetPasswordExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class RecoveryAnswerException extends ApplicationException {

    private final static String MESSAGE = "The recovery answer provided for the account is incorrect.";

    public RecoveryAnswerException() {
        super(MESSAGE);
    }
}
