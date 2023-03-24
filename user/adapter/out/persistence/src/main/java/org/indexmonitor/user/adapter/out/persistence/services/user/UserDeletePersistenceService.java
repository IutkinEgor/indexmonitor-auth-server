package org.indexmonitor.user.adapter.out.persistence.services.user;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.repositories.UserRepository;
import org.indexmonitor.user.application.ports.out.user.UserDeletePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserDeletePersistenceService implements UserDeletePort {

    private final UserRepository repository;

    @Override
    public void delete(BaseId id) {
        repository.deleteById(UserEntity.convertId(id));
    }
}
