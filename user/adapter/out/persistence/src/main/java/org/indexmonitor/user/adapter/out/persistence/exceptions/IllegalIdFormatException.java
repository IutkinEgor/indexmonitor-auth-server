package org.indexmonitor.user.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class IllegalIdFormatException extends AdapterException {
    private static String MESSAGE = " has illegal Id format.";
    public IllegalIdFormatException(String entityName) {
        super(entityName + MESSAGE);
    }
}
