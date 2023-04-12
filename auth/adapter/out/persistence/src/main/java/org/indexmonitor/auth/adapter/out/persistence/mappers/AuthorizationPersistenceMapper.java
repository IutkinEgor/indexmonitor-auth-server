package org.indexmonitor.auth.adapter.out.persistence.mappers;

import org.indexmonitor.auth.adapter.out.persistence.entities.AuthorizationEntity;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

public interface AuthorizationPersistenceMapper {
    AuthorizationEntity modelToEntity(OAuth2Authorization entity);
    OAuth2Authorization entityToModel(AuthorizationEntity model);
}