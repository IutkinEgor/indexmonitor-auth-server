package org.indexmonitor.auth.adapter.out.persistence.services;

import lombok.AllArgsConstructor;
import org.indexmonitor.auth.adapter.out.persistence.entities.AuthorizationEntity;
import org.indexmonitor.auth.adapter.out.persistence.mappers.AuthorizationPersistenceMapper;
import org.indexmonitor.auth.adapter.out.persistence.repositories.AuthorizationRepository;
import org.indexmonitor.auth.application.ports.out.AuthorizationLoadPort;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
class AuthorizationLoadPersistenceService implements AuthorizationLoadPort {
    private final AuthorizationRepository repository;
    private final AuthorizationPersistenceMapper mapper;
    @Override
    public Optional<OAuth2Authorization> findById(String id) {
        Optional<AuthorizationEntity> entity = repository.findById(id);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<OAuth2Authorization> findByTokenValue(String token) {
        Optional<AuthorizationEntity> entity = repository.findByt(id);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }
}
