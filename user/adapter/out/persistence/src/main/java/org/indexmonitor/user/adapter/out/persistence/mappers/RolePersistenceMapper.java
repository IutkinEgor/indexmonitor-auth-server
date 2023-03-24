package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
import org.indexmonitor.user.domain.aggregates.Role;
import org.springframework.data.domain.Page;

public interface RolePersistenceMapper {
    RoleEntity modelToEntity(Role model);
    Role entityToModel(RoleEntity entity);
    BasePage<Role> entityToModel(Page<RoleEntity> entities);
}
