package org.indexmonitor.user.domain.valueObjects;

import org.indexmonitor.common.domain.models.ValueObject;
import org.indexmonitor.common.domain.valueObjects.BaseId;

import java.io.Serializable;
import java.util.Objects;

public class UserAuthority extends ValueObject {
    private final BaseId authorityId;
    private final String authorityName;

    public UserAuthority(BaseId authorityId, String authorityName) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
    }

    public BaseId getAuthorityId() {
        return authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthority that = (UserAuthority) o;

        if (!Objects.equals(authorityId, that.authorityId)) return false;
        return Objects.equals(authorityName, that.authorityName);
    }

    @Override
    public int hashCode() {
        int result = authorityId != null ? authorityId.hashCode() : 0;
        return result;
    }
}
