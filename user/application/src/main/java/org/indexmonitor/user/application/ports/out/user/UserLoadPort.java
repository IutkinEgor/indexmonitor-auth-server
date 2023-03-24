package org.indexmonitor.user.application.ports.out.user;

import org.indexmonitor.common.domain.interfaces.Port;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.User;

import java.util.Optional;

public interface UserLoadPort extends Port {

    BasePage<User> findAll(Integer offset, Integer limit);

    Optional<User> findByUserId(BaseId id);

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    boolean isExist(User user);
    boolean isExistMoreThanOne(User user);

    boolean isExistByEmail(String email);
}
