package org.indexmonitor.user.application.mappers;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.application.ports.in.role.requests.RoleRegisterRequest;
import org.indexmonitor.user.application.ports.in.role.requests.RoleSettingsUpdateRequest;
import org.indexmonitor.user.domain.aggregates.Role;

public interface RoleMapper {

    Role mapRegisterRequest(RoleRegisterRequest request, BaseId id);
    Role mapUpdateRequest(Role role, RoleSettingsUpdateRequest request);
}
