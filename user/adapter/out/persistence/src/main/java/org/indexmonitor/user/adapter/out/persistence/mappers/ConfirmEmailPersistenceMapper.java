package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.user.adapter.out.persistence.entities.ConfirmEmailEntity;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;

public interface ConfirmEmailPersistenceMapper {

    ConfirmEmailEntity modelToEntity(ConfirmEmail model);
    ConfirmEmail entityToModel(ConfirmEmailEntity entity);
}
