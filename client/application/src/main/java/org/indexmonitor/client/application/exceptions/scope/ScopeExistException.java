package org.indexmonitor.client.application.exceptions.scope;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ScopeExistException extends ApplicationException {
    private final static String MESSAGE = "Scope with provided name already exist.";

    public ScopeExistException() {
        super(MESSAGE);
    }
}
