package org.indexmonitor.user.application.ports.out.authority;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.Authority;

import java.util.Optional;
import java.util.Set;

public interface AuthorityLoadPort {

    BasePage<Authority> findAll(Integer page, Integer size);
    Set<Authority> findAllByName(Set<String> names);
    Optional<Authority> findById(BaseId id);

    boolean isExist(Authority authority);

    boolean isExistById(BaseId id);

    boolean isExistByName(String name);

    boolean isAuthorityUsedByUser(String authorityId);
}
