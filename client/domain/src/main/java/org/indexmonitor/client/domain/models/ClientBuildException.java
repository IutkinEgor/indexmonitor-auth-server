package org.indexmonitor.client.domain.models;

import org.indexmonitor.common.domain.exceptions.DomainException;

public class ClientBuildException extends DomainException {

    public ClientBuildException(String message) {
        super(message);
    }
}
