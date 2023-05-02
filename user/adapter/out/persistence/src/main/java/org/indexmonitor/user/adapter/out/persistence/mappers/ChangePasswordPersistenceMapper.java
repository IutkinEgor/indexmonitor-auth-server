package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.user.adapter.out.persistence.entities.ChangePasswordEntity;
import org.indexmonitor.user.domain.aggregates.ChangePassword;

public interface ChangePasswordPersistenceMapper {
    ChangePasswordEntity modelToEntity(ChangePassword model);
    ChangePassword entityToModel(ChangePasswordEntity entity);
}
