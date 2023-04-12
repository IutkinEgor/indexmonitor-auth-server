package org.indexmonitor.auth.adapter.out.persistence.services;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.adapter.out.persistence.mappers.AuthorizationPersistenceMapper;
import org.indexmonitor.auth.adapter.out.persistence.repositories.AuthorizationRepository;
import org.indexmonitor.auth.application.ports.out.AuthorizationSavePort;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthorizationSavePersistenceService implements AuthorizationSavePort {

    private final AuthorizationRepository repository;
    private final AuthorizationPersistenceMapper mapper;

    @Override
    public void save(OAuth2Authorization authorization) {
        repository.save(mapper.modelToEntity(authorization));
    }
}
