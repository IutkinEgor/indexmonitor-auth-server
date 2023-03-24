package org.indexmonitor.common.domain.exceptions;

public abstract class ApplicationException extends RuntimeException{

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
