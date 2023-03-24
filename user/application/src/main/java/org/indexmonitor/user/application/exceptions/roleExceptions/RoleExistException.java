package org.indexmonitor.user.application.exceptions.roleExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class RoleExistException extends ApplicationException {
    private final static String MESSAGE = "Role exist.";
    public RoleExistException() { super(MESSAGE); }
}
