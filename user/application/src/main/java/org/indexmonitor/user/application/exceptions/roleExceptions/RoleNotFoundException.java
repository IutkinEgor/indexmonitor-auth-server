package org.indexmonitor.user.application.exceptions.roleExceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class RoleNotFoundException extends ApplicationException {
    private final static String MESSAGE = "Role not found.";
    public RoleNotFoundException() { super(MESSAGE); }
}
