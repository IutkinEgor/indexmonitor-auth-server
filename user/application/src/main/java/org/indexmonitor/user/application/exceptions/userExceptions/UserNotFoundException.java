package org.indexmonitor.user.application.exceptions.userExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class UserNotFoundException extends ApplicationException {

    private final static String MESSAGE = "User not found.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
