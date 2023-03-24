package org.indexmonitor.client.application.exceptions.scope;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ScopeUsedByClientException extends ApplicationException {
    private final static String MESSAGE = "Scope used by client.";
    public ScopeUsedByClientException() {
        super(MESSAGE);
    }
}
