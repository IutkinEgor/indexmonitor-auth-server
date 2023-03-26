package org.indexmonitor.user.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.domain.valueObjects.Token;

public class ConfirmEmail extends AggregateRoot<BaseId> {
    private final User user;
    private final Token token;
    private final String confirmLink;
    private final String redirectLink;

    public ConfirmEmail(User user, Token token, String confirmLink, String redirectLink) {
        super(user.getId());
        this.token = token;
        this.user = user;
        this.confirmLink = confirmLink;
        this.redirectLink = redirectLink;
    }

    public Token getToken() {
        return token;
    }
    public User getUser() {
        return user;
    }
    public String getConfirmLink(){
        return confirmLink;
    }
    public String getRedirectLink() {
        return redirectLink;
    }
}
