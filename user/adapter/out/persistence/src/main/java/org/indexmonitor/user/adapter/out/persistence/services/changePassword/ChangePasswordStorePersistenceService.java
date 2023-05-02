package org.indexmonitor.user.adapter.out.persistence.services.changePassword;

import lombok.AllArgsConstructor;
import org.indexmonitor.user.adapter.out.persistence.mappers.ChangePasswordPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.ChangePasswordRepository;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordStorePort;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ChangePasswordStorePersistenceService implements ChangePasswordStorePort {
    private final ChangePasswordRepository repository;
    private final ChangePasswordPersistenceMapper mapper;

    @Override
    public void store(ChangePassword changePassword) {
        if(changePassword == null){
            throw new NullPointerException("Change password is NULL.");
        }
        repository.save(mapper.modelToEntity(changePassword));
    }
}
