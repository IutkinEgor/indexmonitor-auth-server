package org.indexmonitor.client.application.exceptions.client;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

import java.util.Set;

public class ClientRegisterException extends ApplicationException {

    private final static String MESSAGE = "Client has an invalid fields value.";

    private final Set<String> errors;

    public ClientRegisterException(Set<String> errors) {
        super(MESSAGE);
        this.errors = errors;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
