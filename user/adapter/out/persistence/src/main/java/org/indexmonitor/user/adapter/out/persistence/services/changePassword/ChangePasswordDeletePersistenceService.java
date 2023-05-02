package org.indexmonitor.user.adapter.out.persistence.services.changePassword;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.ChangePasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.repositories.ChangePasswordRepository;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordDeletePort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ChangePasswordDeletePersistenceService implements ChangePasswordDeletePort {

    private final ChangePasswordRepository repository;

    @Override
    public void deleteById(BaseId id) {
        repository.deleteById(ChangePasswordEntity.convertId(id));
    }
}
