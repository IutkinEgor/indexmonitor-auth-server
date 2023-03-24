package org.indexmonitor.client.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class ClientExistException extends ApplicationException {

    private static final String MESSAGE = "Client with provided client ID, Name or Origin already exist.";

    public ClientExistException() {
        super(MESSAGE);
    }
}
