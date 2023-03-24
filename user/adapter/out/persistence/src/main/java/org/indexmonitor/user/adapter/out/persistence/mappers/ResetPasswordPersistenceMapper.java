package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.user.adapter.out.persistence.entities.ResetPasswordEntity;
import org.indexmonitor.user.domain.aggregates.ResetPassword;

public interface ResetPasswordPersistenceMapper {

    ResetPasswordEntity modelToEntity(ResetPassword model);
    ResetPassword entityToModel(ResetPasswordEntity entity);
}
