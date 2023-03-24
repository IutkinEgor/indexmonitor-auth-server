package org.indexmonitor.user.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class AuthorityIllegalIdException extends AdapterException {
    private static String MESSAGE = "Authority Id has illegal format.";
    public AuthorityIllegalIdException() {
        super(MESSAGE);
    }
}
