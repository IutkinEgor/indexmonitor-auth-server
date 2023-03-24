package org.indexmonitor.user.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.util.Objects;

public class UserRole extends ValueObject {
    private final BaseId roleId;
    private final String roleName;

    public UserRole(BaseId roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public BaseId getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (!Objects.equals(roleId, userRole.roleId)) return false;
        return Objects.equals(roleName, userRole.roleName);
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        return result;
    }
}
