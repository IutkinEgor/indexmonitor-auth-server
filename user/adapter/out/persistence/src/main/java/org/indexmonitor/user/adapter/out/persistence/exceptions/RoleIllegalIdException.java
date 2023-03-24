package org.indexmonitor.user.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class RoleIllegalIdException extends AdapterException {

    private static String MESSAGE = "Role Id has illegal format.";

    public RoleIllegalIdException() {
        super(MESSAGE);
    }
}
