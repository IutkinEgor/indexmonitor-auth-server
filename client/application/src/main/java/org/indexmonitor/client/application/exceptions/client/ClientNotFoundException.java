package org.indexmonitor.client.application.exceptions.client;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ClientNotFoundException extends ApplicationException {

    private final static String MESSAGE = "Client not found.";

    public ClientNotFoundException() {
        super(MESSAGE);
    }
}
