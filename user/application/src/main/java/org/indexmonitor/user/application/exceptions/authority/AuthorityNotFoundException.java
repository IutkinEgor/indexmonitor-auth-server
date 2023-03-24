package org.indexmonitor.user.application.exceptions.authority;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class AuthorityNotFoundException extends ApplicationException {
    private final static String MESSAGE = "Authority not found.";
    public AuthorityNotFoundException() { super(MESSAGE); }
}
