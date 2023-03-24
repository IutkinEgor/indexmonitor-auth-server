package org.indexmonitor.client.application.exceptions.scope;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ScopeNotFoundException extends ApplicationException {

    private final static String MESSAGE = "Scope not found.";

    public ScopeNotFoundException() {
        super(MESSAGE);
    }
}
