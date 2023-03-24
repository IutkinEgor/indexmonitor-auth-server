package org.indexmonitor.user.adapter.out.persistence.mappers;

import org.indexmonitor.common.domain.valueObjects.BasePage;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.domain.aggregates.User;
import org.springframework.data.domain.Page;

public interface UserPersistenceMapper {

    UserEntity modelToEntity(User model);
    User entityToModel(UserEntity entity);
    BasePage<User> entityToModel(Page<UserEntity> entities);
}
