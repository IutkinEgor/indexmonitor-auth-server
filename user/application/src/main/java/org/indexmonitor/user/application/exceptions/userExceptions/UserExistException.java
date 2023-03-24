package org.indexmonitor.user.application.exceptions.userExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;
import org.indexmonitor.user.application.ports.in.user.requests.UserRegisterRequest;

public class UserExistException extends ApplicationException {

    private final static String MESSAGE = "User exist.";
    private UserRegisterRequest command;
    public UserExistException(UserRegisterRequest command) {
        super(MESSAGE);
        this.command = command;
    }
    public UserExistException() {
        super(MESSAGE);
    }

    public UserRegisterRequest getRegisterUserCommand() {
        return command;
    }
}
