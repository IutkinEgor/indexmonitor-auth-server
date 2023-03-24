package org.indexmonitor.user.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class UserProfileIllegalIdException extends AdapterException {

    private static String MESSAGE = "User Profile Id has illegal format.";

    public UserProfileIllegalIdException() {
        super(MESSAGE);
    }
}
