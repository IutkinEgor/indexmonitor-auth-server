package org.indexmonitor.user.adapter.out.persistence.services;

import org.indexmonitor.common.domain.valueObjects.BaseId;
import org.indexmonitor.user.adapter.out.persistence.entities.ResetPasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.entities.UserEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ResetPasswordPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.repositories.ResetPasswordRepository;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordDeletePort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordLoadPort;
import org.indexmonitor.user.application.ports.out.resetPassword.ResetPasswordStorePort;
import org.indexmonitor.user.domain.aggregates.ResetPassword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ResetPasswordPersistenceServiceDeleteLoadStorePort implements ResetPasswordLoadPort, ResetPasswordStorePort, ResetPasswordDeletePort {

    private final ResetPasswordRepository repository;
    private final ResetPasswordPersistenceMapper mapper;

    @Override
    public Optional<ResetPassword> findById(BaseId id) {
        if(id == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<ResetPasswordEntity> entity = repository.findById(ResetPasswordEntity.convertId(id));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ResetPassword> findByUserId(BaseId userId) {
        if(userId == null){
            throw new NullPointerException("User id is NULL.");
        }
        Optional<ResetPasswordEntity> entity = repository.findByUserId(UserEntity.convertId(userId));
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public Optional<ResetPassword> findByToken(String token) {
        if(token == null || token.isEmpty()){
            throw new NullPointerException("Token is NULL.");
        }
        Optional<ResetPasswordEntity> entity = repository.findByToken(token);
        if(entity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(mapper.entityToModel(entity.get()));
    }

    @Override
    public void store(ResetPassword resetPassword) {
        if(resetPassword == null){
            throw new NullPointerException("Reset password is NULL.");
        }
        repository.save(mapper.modelToEntity(resetPassword));
    }

    @Override
    public void deleteById(BaseId id) {
        if(id == null) {
            throw new NullPointerException("Id is NULL.");
        }
        repository.deleteById(ResetPasswordEntity.convertId(id));
    }

    @Override
    public void deleteByUserId(BaseId userId) {
        if(userId == null) {
            throw new NullPointerException("User Id is NULL.");
        }
        repository.deleteById(ResetPasswordEntity.convertId(userId));
    }


}
