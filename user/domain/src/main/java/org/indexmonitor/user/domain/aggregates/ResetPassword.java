package org.indexmonitor.user.domain.aggregates;

import org.indexmonitor.common.domain.models.AggregateRoot;
import org.indexmonitor.user.domain.valueObjects.Token;

import java.util.UUID;

public class ResetPassword  extends AggregateRoot<UUID> {

    private final Token token;
    private final User user;

    public ResetPassword(UUID uuid, Token token, User user) {
        super(uuid);
        this.token = token;
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

}
