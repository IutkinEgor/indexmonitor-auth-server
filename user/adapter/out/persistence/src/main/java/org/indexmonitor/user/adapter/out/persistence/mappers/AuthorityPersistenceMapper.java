package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.AuthorityEntity;
import org.indexmonitor.user.domain.aggregates.Authority;
import org.springframework.data.domain.Page;

public interface AuthorityPersistenceMapper {
    AuthorityEntity modelToEntity(Authority model);
    Authority entityToModel(AuthorityEntity entity);
    BasePage<Authority> entityToModel(Page<AuthorityEntity> entities);
}
