package org.indexmonitor.client.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class ScopeIllegalIdException extends AdapterException {

    private static String MESSAGE = "Illegal Scope Id format. Expected format: 'UUID'.";
    public ScopeIllegalIdException() {
        super(MESSAGE);
    }
}
