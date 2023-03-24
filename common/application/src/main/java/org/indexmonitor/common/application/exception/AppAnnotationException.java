package org.indexmonitor.common.application.exception;

public class AppAnnotationException extends Exception{

    public AppAnnotationException(String message) {
        super(message);
    }

    public AppAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }
}
