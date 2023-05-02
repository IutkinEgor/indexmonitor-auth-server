package org.indexmonitor.user.application.exceptions.userExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class UserCredentialsException extends ApplicationException {

    private final static String MESSAGE = "Wrong credentials";

    public UserCredentialsException() {
        super(MESSAGE);
    }

    public UserCredentialsException(String message) {
        super(message);
    }
}
