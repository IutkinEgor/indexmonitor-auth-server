package org.indexmonitor.user.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class UserIllegalIdException extends AdapterException {

    private static String MESSAGE = "User Id has illegal format.";

    public UserIllegalIdException() {
        super(MESSAGE);
    }
}
