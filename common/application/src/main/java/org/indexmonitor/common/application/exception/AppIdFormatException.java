package org.indexmonitor.common.application.exception;

import org.indexmonitor.common.domain.exceptions.AdapterException;

public class AppIdFormatException extends AdapterException {
    private final static String MESSAGE = "Id format error.";

    public AppIdFormatException() { super(MESSAGE); }
}
