package org.indexmonitor.auth.adapter.out.persistence.services;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.adapter.out.persistence.mappers.AuthorizationPersistenceMapper;
import org.indexmonitor.auth.adapter.out.persistence.repositories.AuthorizationRepository;
import org.indexmonitor.auth.application.ports.out.AuthorizationRemovePort;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthorizationRemovePersistenceService implements AuthorizationRemovePort {
    private final AuthorizationRepository repository;
    @Override
    public void remove(OAuth2Authorization authorization) {
        repository.deleteById(authorization.getId());
    }
}
