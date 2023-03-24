package org.indexmonitor.client.adapter.out.persistence.exceptions;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class ClientNotFoundException extends AdapterException {

    private final static String MESSAGE = "Client not found.";

    public ClientNotFoundException() {
        super(MESSAGE);
    }

}
