package org.indexmonitor.user.application.ports.out.role;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.domain.aggregates.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleLoadPort {
    BasePage<Role> findAll(Integer page, Integer size);
    Set<Role> findAllByName(Set<String> name);
    Optional<Role> findById(BaseId id);
    boolean isExist(Role authority);

    boolean isExistById(BaseId id);

    boolean isExistByName(String name);

    boolean isRoleUsedByUser(String roleId);
}
