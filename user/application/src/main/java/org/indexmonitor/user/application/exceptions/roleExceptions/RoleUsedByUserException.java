package org.indexmonitor.user.application.exceptions.roleExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class RoleUsedByUserException extends ApplicationException {
    private static String MESSAGE = "Role is used by user(s). Delete operation is not allowed.";

    public RoleUsedByUserException() {
        super(MESSAGE);
    }
}
