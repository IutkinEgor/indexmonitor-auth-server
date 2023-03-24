package org.indexmonitor.client.application.exceptions.client;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ClientExistException extends ApplicationException {

    private final static String MESSAGE = "Client with provided Client Id, Name or Origin already exist.";

    public ClientExistException() {
        super(MESSAGE);
    }
}
