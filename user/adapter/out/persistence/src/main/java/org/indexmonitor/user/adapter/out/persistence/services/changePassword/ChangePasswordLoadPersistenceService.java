package org.indexmonitor.user.adapter.out.persistence.services.changePassword;

import lombok.AllArgsConstructor;
import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.ChangePasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ChangePasswordPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.ChangePasswordRepository;
import org.indexmonitor.user.application.ports.out.changePassword.ChangePasswordLoadPort;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class ChangePasswordLoadPersistenceService implements ChangePasswordLoadPort {
    private final ChangePasswordRepository repository;
    private final ChangePasswordPersistenceMapper mapper;

    @Override
    public Optional<ChangePassword> findById(BaseId id) {
        if(id == null){
            throw new NullPointerException("Change password id is NULL.");
        }
        Optional<ChangePasswordEntity> entity = repository.findById(ChangePasswordEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ChangePassword> findByUserId(BaseId userId) {
        if(userId == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<ChangePasswordEntity> entity = repository.findByUserId(UserEntity.convertId(userId));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ChangePassword> findByToken(String token) {
        if(token == null || token.isEmpty()){
            throw new NullPointerException("Token is NULL.");
        }
        Optional<ChangePasswordEntity> entity = repository.findByToken(token);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }
}
