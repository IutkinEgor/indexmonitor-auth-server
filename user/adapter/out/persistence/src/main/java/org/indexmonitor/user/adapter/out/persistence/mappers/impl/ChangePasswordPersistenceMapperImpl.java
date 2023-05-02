package org.indexmonitor.user.adapter.out.persistence.mappers.impl;

import lombok.AllArgsConstructor;
import org.indexmonitor.user.adapter.out.persistence.entities.ChangePasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.entities.ResetPasswordEntity;
import org.indexmonitor.user.adapter.out.persistence.mappers.ChangePasswordPersistenceMapper;
import org.indexmonitor.user.adapter.out.persistence.mappers.UserPersistenceMapper;
import org.indexmonitor.user.domain.aggregates.ChangePassword;
import org.indexmonitor.user.domain.valueObjects.Token;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ChangePasswordPersistenceMapperImpl implements ChangePasswordPersistenceMapper {

    private final UserPersistenceMapper userMapper;

    @Override
    public ChangePasswordEntity modelToEntity(ChangePassword model) {
        return  ChangePasswordEntity.builder()
                .id(ResetPasswordEntity.convertId(model.getId()))
                .tokenHash(model.getToken().getTokenHash())
                .tokenAlgorithm(model.getToken().getAlgorithm())
                .issuedAt(model.getToken().getIssuedAt())
                .expireAt(model.getToken().getExpireAt())
                .user(userMapper.modelToEntity(model.getUser()))
                .redirectUrl(model.getRedirectLink())
                .build();
    }

    @Override
    public ChangePassword entityToModel(ChangePasswordEntity entity) {
        Token token = Token.builder()
                .tokenHash(entity.getTokenHash())
                .algorithm(entity.getTokenAlgorithm())
                .issuedAt(entity.getIssuedAt())
                .expireAt(entity.getExpireAt())
                .build();
        return new ChangePassword(userMapper.entityToModel(entity.getUser()),token,null, entity.getRedirectUrl());
    }
}
