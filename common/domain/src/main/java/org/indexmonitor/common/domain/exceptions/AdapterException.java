package org.indexmonitor.common.domain.exceptions;

public abstract class AdapterException extends RuntimeException {

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(String message, Throwable cause) { super(message, cause);  }
}
