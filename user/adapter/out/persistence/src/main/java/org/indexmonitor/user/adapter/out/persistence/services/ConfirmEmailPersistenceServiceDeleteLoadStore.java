package org.indexmonitor.user.adapter.out.persistence.services;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.ConfirmEmailEntity;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ConfirmEmailPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.ConfirmEmailRepository;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailDeletePort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailLoadPort;
import org.indexmonitor.user.application.ports.out.confirmEmail.ConfirmEmailStorePort;
import org.indexmonitor.user.domain.aggregates.ConfirmEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
class ConfirmEmailPersistenceServiceDeleteLoadStore implements ConfirmEmailLoadPort, ConfirmEmailStorePort, ConfirmEmailDeletePort {

    private final ConfirmEmailRepository repository;
    private final ConfirmEmailPersistenceMapper mapper;

    @Override
    public Optional<ConfirmEmail> findById(BaseId id) {
        if(id == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<ConfirmEmailEntity> entity = repository.findById(ConfirmEmailEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ConfirmEmail> findByUserId(BaseId id) {
        if(id == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<ConfirmEmailEntity> entity = repository.findByUserId(UserEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ConfirmEmail> findByToken(String token) {
        if(token == null || token.isEmpty()){
            throw new NullPointerException("Token is NULL.");
        }
        Optional<ConfirmEmailEntity> entity = repository.findByToken(token);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public BaseId generateId() {
        return BaseId.map(UUID.randomUUID());
    }

    @Override
    public void store(ConfirmEmail confirmEmail) {
        if(confirmEmail == null){
            throw new NullPointerException("Confirm email is NULL.");
        }
        repository.save(mapper.modelToEntity(confirmEmail));
    }

    @Override
    public void deleteById(BaseId id) {
        if(id == null) {
            throw new NullPointerException("Id is NULL.");
        }
        repository.deleteById(ConfirmEmailEntity.convertId(id));
    }

    @Override
    public void deleteByUserId(BaseId userId) {
        if(userId == null) {
            throw new NullPointerException("User Id is NULL.");
        }
        repository.deleteByUserUserId(UserEntity.convertId(userId));
    }
}
