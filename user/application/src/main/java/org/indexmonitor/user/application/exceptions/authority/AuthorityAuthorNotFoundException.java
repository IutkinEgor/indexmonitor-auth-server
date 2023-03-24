package org.indexmonitor.user.application.exceptions.authority;

import org.indexmonitor.common.domain.exceptions.ApplicationException;

public class AuthorityAuthorNotFoundException extends ApplicationException {

    private final static String MESSAGE = "Authority author not found.";

    public AuthorityAuthorNotFoundException() {
        super(MESSAGE);
    }
}
