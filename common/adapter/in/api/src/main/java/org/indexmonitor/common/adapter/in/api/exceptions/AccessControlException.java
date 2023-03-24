package org.indexmonitor.common.adapter.in.api.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class AccessControlException extends AdapterException {
    private static String MESSAGE = "Does not have required permissions";

    public AccessControlException() {
        super(MESSAGE);
    }
    public AccessControlException(String message) {
        super(message);
    }
}
