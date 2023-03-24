package org.indexmonitor.user.application.exceptions.authority;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class AuthorityExistException extends ApplicationException {
    private final static String MESSAGE = "Authority exist.";
    public AuthorityExistException() { super(MESSAGE); }
}
