package org.indexmonitor.user.application.ports.out;

import org.indexmonitor.user.domain.aggregates.Authority;
import org.indexmonitor.user.domain.aggregates.Role;
import org.indexmonitor.user.domain.aggregates.User;

public interface UserAppSeedPort {

    void seed(User user, Role role, Authority authority);
}
