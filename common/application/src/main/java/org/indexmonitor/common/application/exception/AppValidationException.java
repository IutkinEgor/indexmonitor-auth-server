package org.indexmonitor.common.application.exception;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

import java.util.HashSet;
import java.util.Set;

public class AppValidationException extends ApplicationException {
    private final static String MESSAGE = "Validation error.";
    private Set<String> errors;

    public AppValidationException(Set<String> errors) {
        super(MESSAGE);
        if ( errors == null ) {
            this.errors = null;
        }
        else {
            this.errors = new HashSet<>( errors );
        }
    }

    public Set<String> getErrors() {
        return errors;
    }
}
