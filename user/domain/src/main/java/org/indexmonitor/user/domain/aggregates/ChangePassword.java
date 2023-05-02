package org.indexmonitor.user.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.valueObjects.Token;

public class ChangePassword extends AggregateRoot<BaseId> {
    private final User user;
    private final Token token;
    private final String confirmLink;
    private final String redirectLink;

    public ChangePassword(User user,Token token, String confirmLink, String redirectLink) {
        super(user.getId());
        this.user = user;
        this.token = token;
        this.confirmLink = confirmLink;
        this.redirectLink = redirectLink;
    }

    public Token getToken() {
        return token;
    }
    public User getUser() {
        return user;
    }
    public String getConfirmLink() {
        return confirmLink;
    }
    public String getRedirectLink() {
        return redirectLink;
    }
}
